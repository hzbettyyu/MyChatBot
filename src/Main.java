/**
 * Jinghui Yu
 * Created: 3/2/2015
 *
 * The main class the run MyChatBot that loads the information to mybot/set
 * and setup the chat bot to start chatting.
 */

import org.alicebot.ab.*;


public class Main {

    public static void main(String[] args) {
        MagicStrings.setRootPath();

        AIMLProcessor.extension = new PCAIMLProcessorExtension();
        mainFunction(args);
    }

    public static void mainFunction(String[] args) {
        //TODO: load info from a given file and classify the information

        String botName = "mybot";
        String action = "chat";
        Bot bot = new Bot(botName, MagicStrings.root_path, action); //

        boolean doWrites = !action.equals("chat-app");
        TestAB.testChat(bot, doWrites, MagicBooleans.trace_mode);
    }


}
