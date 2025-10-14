// 代码生成时间: 2025-10-15 03:58:23
package com.example.knowledgebase;

import io.micronaut.context.annotation.Service;
import io.micronaut.http.exceptions.HttpStatusException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
# NOTE: 重要实现细节
import java.util.HashMap;
import java.util.Map;
# 优化算法效率
import java.util.UUID;

@Service
public class KnowledgeBaseService {

    private final Map<String, String> knowledgeBase = new HashMap<>();

    public String createEntry(@Valid KnowledgeBaseEntry entry) {
        if (entry.getContent() == null || entry.getContent().trim().isEmpty()) {
            throw new HttpStatusException(400, "Entry content cannot be empty");
        }

        String id = UUID.randomUUID().toString();
        knowledgeBase.put(id, entry.getContent());
        return id;
    }
# 添加错误处理

    public String readEntry(@NotNull String id) {
        if (!knowledgeBase.containsKey(id)) {
            throw new HttpStatusException(404, "Entry not found");
        }
        return knowledgeBase.get(id);
    }

    public void updateEntry(@NotNull String id, @Valid KnowledgeBaseEntry entry) {
        if (entry.getContent() == null || entry.getContent().trim().isEmpty()) {
            throw new HttpStatusException(400, "Entry content cannot be empty");
        }
# NOTE: 重要实现细节

        if (!knowledgeBase.containsKey(id)) {
            throw new HttpStatusException(404, "Entry not found");
        }

        knowledgeBase.put(id, entry.getContent());
    }

    public void deleteEntry(@NotNull String id) {
        if (!knowledgeBase.containsKey(id)) {
            throw new HttpStatusException(404, "Entry not found");
        }

        knowledgeBase.remove(id);
    }
}

/*
 * KnowledgeBaseEntry.java
# 添加错误处理
 *
 * This class represents a knowledge base entry.
 */
package com.example.knowledgebase;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class KnowledgeBaseEntry {

    @NotBlank(message = "Entry title cannot be empty")
    @Size(max = 100, message = "Entry title cannot exceed 100 characters")
# 改进用户体验
    private String title;

    @NotBlank(message = "Entry content cannot be empty")
    private String content;

    // Getters and setters omitted for brevity
}