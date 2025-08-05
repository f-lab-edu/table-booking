package me.app.tablebooking.adapter.in.web;

import lombok.RequiredArgsConstructor;
import me.app.tablebooking.adapter.in.web.request.RestaurantRequest;
import me.app.tablebooking.application.domain.model.Category;
import me.app.tablebooking.application.domain.model.Member;
import me.app.tablebooking.application.domain.model.PriceRange;
import me.app.tablebooking.application.port.in.RestaurantRegisterCommand;
import me.app.tablebooking.application.port.in.RestaurantRegisterUseCase;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantRegisterUseCase restaurantRegisterUseCase;

    @PreAuthorize("hasAuthority('OWNER')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> create(
            @RequestPart(value = "request", required = true) RestaurantRequest request,
            @RequestPart(value = "images", required = true) List<MultipartFile> images,
            @AuthenticationPrincipal Member member) {
        RestaurantRegisterCommand command = new RestaurantRegisterCommand(
                member,
                Category.valueOf(request.getCategory().toUpperCase()),
                PriceRange.valueOf(request.getPriceRange().toUpperCase()),
                request.getContent(),
                request.getLocation(),
                request.getPhoneNumber()
        );

        restaurantRegisterUseCase.create(command, images);
        return ResponseEntity.ok("레스트랑 생성이 완료되었습니다. ");
    }
}
