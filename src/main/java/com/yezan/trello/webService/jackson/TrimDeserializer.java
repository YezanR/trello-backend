package com.yezan.trello.webService.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.yezan.trello.entity.Board;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;


class TrimDeserializer extends JsonDeserializer<Board> {

    @Override
    public Board deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException {
        return new Board();
    }
}
