package vlad.kuchuk.clevertecvideohostingapi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vlad.kuchuk.clevertecvideohostingapi.dto.ChannelDto;
import vlad.kuchuk.clevertecvideohostingapi.commonExceptionUtil.exceptions.PhotoOperationException;
import vlad.kuchuk.clevertecvideohostingapi.service.ChannelService;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/channel")
@AllArgsConstructor
public class ChannelController {

    private final ChannelService channelService;

    @PostMapping
    public ResponseEntity<ChannelDto> createChannel(
            @RequestPart("channel") @Valid ChannelDto channelDto,
            @RequestPart(value = "avatar", required = false) MultipartFile file
            ) {
        try {
            if (!file.isEmpty()) {
                channelDto.setAvatar(file.getBytes());
            }
            ChannelDto createdChannelDto = channelService.saveChannel(channelDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(createdChannelDto);
        } catch (IOException e) {
            throw new PhotoOperationException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChannelDto> getChannel(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(channelService.getChannelById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChannelDto> updateChannel(
            @PathVariable("id") Long id,
            @RequestPart("channel") @Valid ChannelDto channelDto,
            @RequestPart(value = "avatar", required = false) MultipartFile file
    ) {
        try {
            if (Objects.nonNull(file)) {
                channelDto.setAvatar(file.getBytes());
            }
            ChannelDto updatedChannelDto = channelService.updateChannel(channelDto, id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(updatedChannelDto);
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
