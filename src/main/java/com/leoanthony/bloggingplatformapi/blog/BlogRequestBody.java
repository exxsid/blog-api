package com.leoanthony.bloggingplatformapi.blog;

import org.springframework.stereotype.Component;

import java.util.List;


public record BlogRequestBody(String title, String content, String category, List<String> tags) {
}
