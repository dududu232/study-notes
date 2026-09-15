package com.tiamo.dt.common.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * 自定义JSON反序列化，将JSON对象或数组转换为字符串存储
 */
public class CustomJsonStrDeserializer extends StdDeserializer<String> {

    public CustomJsonStrDeserializer(){
        this(null);
    }

    protected CustomJsonStrDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        // 读取整个JSON结构为JsonNode
        JsonNode node = jsonParser.readValueAsTree();
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();

        // 根据节点类型定制输出格式
        switch (node.getNodeType()) {
            case ARRAY:
            case OBJECT:
                return mapper.writeValueAsString(node);
            default:
                return node.asText();
        }
    }

}
