// 代码生成时间: 2025-10-17 19:36:44
package com.example.rfid;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.server.exceptions.InternalServerException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller("/tags")
public class RfidTagManager {

    private final Map<String, String> rfidTags = new HashMap<>();

    /**
     * 添加一个新的RFID标签
     *
     * @param tagId RFID标签的唯一标识符
     * @param tagName RFID标签的名称
     * @return 返回添加成功的RFID标签信息
     */
    @Get("/add/{tagId}/{tagName}")
    public String addRfidTag(@PathVariable String tagId, @PathVariable String tagName) {
        if (rfidTags.containsKey(tagId)) {
            throw new InternalServerException("Tag ID already exists");
        }
        rfidTags.put(tagId, tagName);
        return "Tag added: " + tagName;
    }

    /**
     * 获取RFID标签信息
     *
     * @param tagId RFID标签的唯一标识符
     * @return 返回RFID标签的名称
     */
    @Get("/get/{tagId}")
    public String getRfidTag(@PathVariable String tagId) {
        if (!rfidTags.containsKey(tagId)) {
            throw new InternalServerException("Tag not found");
        }
        return rfidTags.get(tagId);
    }

    /**
     * 更新RFID标签名称
     *
     * @param tagId RFID标签的唯一标识符
     * @param newTagName 新的RFID标签名称
     * @return 返回更新后的RFID标签信息
     */
    @Get("/update/{tagId}/{newTagName}")
    public String updateRfidTag(@PathVariable String tagId, @PathVariable String newTagName) {
        if (!rfidTags.containsKey(tagId)) {
            throw new InternalServerException("Tag not found");
        }
        rfidTags.put(tagId, newTagName);
        return "Tag updated: " + newTagName;
    }

    /**
     * 删除RFID标签
     *
     * @param tagId RFID标签的唯一标识符
     * @return 返回删除操作的结果
     */
    @Get("/delete/{tagId}")
    public String deleteRfidTag(@PathVariable String tagId) {
        if (!rfidTags.containsKey(tagId)) {
            throw new InternalServerException("Tag not found");
        }
        rfidTags.remove(tagId);
        return "Tag deleted";
    }
}
