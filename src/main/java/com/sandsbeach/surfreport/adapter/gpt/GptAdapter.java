package com.sandsbeach.surfreport.adapter.gpt;

import com.sandsbeach.surfreport.model.Article;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class GptAdapter {

    @Value("${gpt.prompt.article}")
    private String genericArticlePrompt;
    @Value("${gpt.prompt.title}")
    private String genericTitlePrompt;
    private OpenAiService openAiService;

    @Autowired
    public GptAdapter(@Value("${gpt.token}") String token) {
        openAiService = new OpenAiService(token, 60_000);
    }

    public Article generateArticle(String prompt) {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(String.join("\n", genericArticlePrompt, prompt))
                .model("text-davinci-002")
                .maxTokens(1000)
                .temperature(.9)
                .echo(false)
                .build();

        log.info("Prompt: {}", completionRequest.getPrompt());

        List<CompletionChoice> responses = openAiService.createCompletion(completionRequest).getChoices();

        String body = responses.get(0).getText().trim().replaceFirst("^.*?([A-Z])", "$1");;
        
        return Article.builder()
                .title(generateTitle(body))
                .body(body)
                .build();
    }

    private String generateTitle(String body) {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(String.join("\n", genericTitlePrompt, "\"" + body + "\""))
                .model("text-davinci-002")
                .maxTokens(1000)
                .echo(false)
                .build();

        List<CompletionChoice> responses = openAiService.createCompletion(completionRequest).getChoices();

        return responses.get(0).getText().trim();
    }
}
