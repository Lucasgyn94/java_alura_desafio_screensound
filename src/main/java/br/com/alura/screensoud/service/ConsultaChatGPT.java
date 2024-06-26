package br.com.alura.screensoud.service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Arrays;

public class ConsultaChatGPT {

    public static String obterInformacao(String texto) {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("OPENAI_APIKEY");
        OpenAiService service = new OpenAiService(apiKey);

        ChatCompletionRequest requisicao = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(Arrays.asList(new ChatMessage("user", "Me fale sobre o artista: " + texto)))
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var resposta = service.createChatCompletion(requisicao);
        return resposta.getChoices().get(0).getMessage().getContent();
    }

}
