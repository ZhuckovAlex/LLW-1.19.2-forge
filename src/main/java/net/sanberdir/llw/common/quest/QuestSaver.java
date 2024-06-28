package net.sanberdir.llw.common.quest;

import com.google.gson.Gson;
import net.sanberdir.llw.LLW;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class QuestSaver {
    private static final Gson gson = new Gson();

    public static void saveQuests(List<Quest> quests, String directoryPath) {
        for (Quest quest : quests) {
            String questJson = gson.toJson(quest);
            String filePath = directoryPath + "/" + quest.getId() + ".json";
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(questJson);
            } catch (IOException e) {
                LLW.LOGGER.error("Ошибка при сохранении квеста: {}", quest.getId(), e);
            }
        }
    }
}