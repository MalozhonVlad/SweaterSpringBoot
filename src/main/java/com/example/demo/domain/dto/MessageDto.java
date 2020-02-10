package com.example.demo.domain.dto;

import com.example.demo.domain.Message;
import com.example.demo.domain.User;
import com.example.demo.domain.util.MessageHelper;
import lombok.Data;

@Data
public class MessageDto {
    private int id;
    private String text;
    private String tag;
    private User author;
    private String filename;
    private int likes;
    private Boolean meLiked;

    public MessageDto(Message message, int likes, Boolean meLiked) {
        this.id = message.getId();
        this.text = message.getText();
        this.tag = message.getTag();
        this.author = message.getAuthor();
        this.filename = message.getFilename();
        this.likes = likes;
        this.meLiked = meLiked;
    }

    public String getAuthorName() {
        return MessageHelper.getAuthorName(author);
    }


}