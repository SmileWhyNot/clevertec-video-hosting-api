package vlad.kuchuk.clevertecvideohostingapi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vlad.kuchuk.clevertecvideohostingapi.dto.request.ChannelRequest;
import vlad.kuchuk.clevertecvideohostingapi.dto.request.FilteredPageableChannelRequest;
import vlad.kuchuk.clevertecvideohostingapi.dto.response.FilteredChannelInfoResponse;
import vlad.kuchuk.clevertecvideohostingapi.dto.response.FullChannelInfoResponse;
import vlad.kuchuk.clevertecvideohostingapi.service.ChannelService;
import vlad.kuchuk.clevertecvideohostingapi.validator.FileValidation;

@RestController
@RequestMapping("/api/v1/channels")
@AllArgsConstructor
public class ChannelController {

    private final ChannelService channelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FullChannelInfoResponse createChannel(
            @RequestPart("channel") @Valid ChannelRequest channelRequest,
            @RequestPart(value = "avatar", required = false)
            @FileValidation(fileExtensions = {"jpeg", "jpg", "png", "gif"}) MultipartFile file
    ) {
        return channelService.saveChannel(channelRequest, file);
    }

    @GetMapping("/{id}")
    public FullChannelInfoResponse getFullChannelInfo(@PathVariable("id") Long id) {
        return channelService.getFullChannelInfo(id);
    }

    @GetMapping
    public Page<FilteredChannelInfoResponse> getChannelsInfoFilteredPageable(
            @ModelAttribute @Valid FilteredPageableChannelRequest request
    ) {
        return channelService.getAllChannelsSortedPageable(request);
    }

    @SneakyThrows
    @PatchMapping("/{id}")
    public FullChannelInfoResponse updateChannel(
            @PathVariable("id") Long id,
            @RequestPart(value = "channel", required = false) @Valid ChannelRequest channelRequest,
            @RequestPart(value = "avatar", required = false) @FileValidation(fileExtensions = {"jpeg", "jpg", "png", "gif"}) MultipartFile file
    ) {
        return channelService.updateChannel(channelRequest, id, file);
    }

    @DeleteMapping("/{id}")
    public void deleteChannel(
            @PathVariable("id") Long id
    ) {
        channelService.deleteChannel(id);
    }
}