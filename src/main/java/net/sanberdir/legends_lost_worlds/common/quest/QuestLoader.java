package net.sanberdir.legends_lost_worlds.common.quest;

import com.google.gson.Gson;
import net.sanberdir.legends_lost_worlds.LLW;
import net.sanberdir.legends_lost_worlds.common.gui.FolioElements;
import net.sanberdir.legends_lost_worlds.common.utils.FileManager;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class QuestLoader {
    private static final Gson gson = new Gson();
    public static void loadQuests() {
        List<String> quests_path = FileManager.getFileManager("quests");
        for (String quest_path: quests_path) {
            try (InputStream questStream = QuestLoader.class.getClassLoader().getResourceAsStream(quest_path)) {
                Quest quest = gson.fromJson(new InputStreamReader(questStream, StandardCharsets.UTF_8), Quest.class);
                FolioElements.quests.add(quest);
            } catch (Exception e) {
                LLW.LOGGER.error("Ошибка при загрузке квеста: {}", quest_path, e);
            }
        }
    }
}
