package com.example.task1.controller;

import com.example.task1.dto.SocksDto;
import com.example.task1.service.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/socks")
public class SocksController {
    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }


    @Operation(
            summary = "It increases amount (quantity) of socks in Data Base Table",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The increase was successful",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SocksDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The request parameters are missing or have an incorrect format",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SocksDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An error occurred that does not depend on the caller (for example, the DataBase is unavailable)",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SocksDto.class)
                            )
                    )
            }
    )
    @PostMapping(value="/income", consumes="application/json")
    public ResponseEntity<?> incomeSocks(@RequestBody SocksDto socksDto) {
        return ResponseEntity.ok(socksService.incomeSocks(socksDto));
    }


    @Operation(
            summary = "It reduces amount (quantity) of socks in Data Base Table",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The reducing was successful",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SocksDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The request parameters are missing or have an incorrect format",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SocksDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An error occurred that does not depend on the caller (for example, the DataBase is unavailable)",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SocksDto.class)
                            )
                    )
            }
    )
    @PostMapping(value="/outcome", consumes="application/json")
    public ResponseEntity<?> outcomeSocks(@RequestBody SocksDto socksDto) {

        SocksDto socksDtoRes = socksService.outcomeSocks(socksDto);
        if (socksDtoRes == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(socksDtoRes);
        }
    }

    @Operation(
            summary = "Returns the total number of socks that match the request criteria passed in the parameters",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The request was executed, the result is in the response body",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The request parameters are missing or have an incorrect format",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An error occurred that does not depend on the caller (for example, the DataBase is unavailable)",
                            content = @Content()
                    )
            }
    )
    @GetMapping
    public ResponseEntity<?> getSocks(@RequestParam("color") String color,
                           @RequestParam("operation") String operation,
                           @RequestParam("cottonPart") int cottonPart
                           ) {

        Optional<Integer> socksQuantity = socksService.getSocks(color, operation, cottonPart);
        return ResponseEntity.ok(socksQuantity.orElse(0));
    }
}
