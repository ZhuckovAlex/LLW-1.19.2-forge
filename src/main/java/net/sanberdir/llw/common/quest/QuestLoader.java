package net.sanberdir.llw.common.quest;

import com.google.gson.Gson;
import net.sanberdir.llw.LLW;
import net.sanberdir.llw.common.gui.FolioElements;
import net.sanberdir.llw.common.utils.FileManager;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class QuestLoader {
    private static final Gson gson = new Gson();

    public static void loadQuests() {
        List<String> questsPath = FileManager.getFileManager("quests");
        for (String questPath : questsPath) {
            try (InputStream questStream = QuestLoader.class.getClassLoader().getResourceAsStream(questPath)) {
                Quest quest = gson.fromJson(new InputStreamReader(questStream, StandardCharsets.UTF_8), Quest.class);
                if (quest.getId().equals(FileManager.extractFileNameWithoutExtension(questPath))) {
                    FolioElements.quests.add(quest);
                } else {
                    LLW.LOGGER.warn("Error mismatch of id and name in the file: " + questPath);
                }
            } catch (Exception e) {
                LLW.LOGGER.error("Ошибка при загрузке квеста: {}", questPath, e);
            }
        }
    }
}