package vlad.kuchuk.clevertecvideohostingapi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.PhotoOperationException;
import vlad.kuchuk.clevertecvideohostingapi.dto.ChannelDto;
import vlad.kuchuk.clevertecvideohostingapi.dto.FilteredChannelInfoDto;
import vlad.kuchuk.clevertecvideohostingapi.dto.FilteredPageableChannelRequest;
import vlad.kuchuk.clevertecvideohostingapi.dto.FullChannelInfoDto;
import vlad.kuchuk.clevertecvideohostingapi.service.ChannelService;
import vlad.kuchuk.clevertecvideohostingapi.utils.ImageUtils;
import vlad.kuchuk.clevertecvideohostingapi.validator.FileValidation;

import java.io.IOException;
import java.util.Objects;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping("/api/v1/channel")
@AllArgsConstructor
public class ChannelController {

    private final ChannelService channelService;

    @PostMapping
    public ResponseEntity<ChannelDto> createChannel(
            @RequestPart("channel") @Valid ChannelDto channelDto,
            @RequestPart(value = "avatar", required = false) @FileValidation(fileExtensions = {"jpeg", "jpg", "png", "gif"}) MultipartFile file
            ) {
        try {
            if (Objects.nonNull(file)) {
                channelDto.setAvatar(ImageUtils.compressImage(file.getBytes()));
            }
            ChannelDto createdChannelDto = channelService.saveChannel(channelDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(createdChannelDto);
        } catch (IOException e) {
            throw new PhotoOperationException(e.getMessage());
        }
    }

    @SneakyThrows({PhotoOperationException.class, DataFormatException.class, IOException.class})
    @GetMapping("/{id}")
    public ResponseEntity<FullChannelInfoDto> getFullChannelInfo(@PathVariable("id") Long id) {
        FullChannelInfoDto fullChannelInfo = channelService.getFullChannelInfo(id);
        fullChannelInfo.setAvatar(ImageUtils.decompressImage(fullChannelInfo.getAvatar()));
        return ResponseEntity.status(HttpStatus.OK)
                .body(channelService.getFullChannelInfo(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<FilteredChannelInfoDto> getChannelsInfoFilteredPageable(
            @ModelAttribute @Valid FilteredPageableChannelRequest request
    ) {
        return channelService.getAllChannelsSortedPageable(request);
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<FullChannelInfoDto> updateChannel(
            @PathVariable("id") Long id,
            @RequestPart(value = "channel", required = false) @Valid ChannelDto channelDto,
            @RequestPart(value = "avatar", required = false) @FileValidation(fileExtensions = {"jpeg", "jpg", "png", "gif"}) MultipartFile file
    ) {
        try {
            if (Objects.nonNull(file)) {
                channelDto.setAvatar(ImageUtils.compressImage(file.getBytes()));
            }
            FullChannelInfoDto updatedChannelDto = channelService.updateChannel(channelDto, id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(updatedChannelDto.setAvatar(ImageUtils.decompressImage(updatedChannelDto.getAvatar())));
        } catch (IOException e) {
            throw new PhotoOperationException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteChannel(
            @PathVariable("id") Long id
    ) {
        channelService.deleteChannel(id);
    }


}
