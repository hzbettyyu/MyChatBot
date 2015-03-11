/**
 * Jinghui Yu
 * Created: 3/2/2015
 *
 * The main class the run MyChatBot that loads the information to mybot/set
 * and setup the chat bot to start chatting.
 */

import org.alicebot.ab.*;
import org.alicebot.ab.utils.IOUtils;


public class Main {

    public static void main(String[] args) {
        MagicStrings.setRootPath();

        AIMLProcessor.extension = new PCAIMLProcessorExtension();
        mainFunction(args);
    }

    public static void mainFunction(String[] args) {
        //TODO: load info from a given file and classify the information

        String botName = "currentevents";
        String action = "chat";
        Bot bot = new Bot(botName, MagicStrings.root_path, action); //

        boolean doWrites = !action.equals("chat-app");
        startChatByBot(bot, doWrites, MagicBooleans.trace_mode);
    }

    public static void startChatByBot(Bot bot, boolean doWrites, boolean traceMode) {
        Chat chatSession = new Chat(bot, doWrites);
        bot.brain.nodeStats();
        MagicBooleans.trace_mode = traceMode;
        String textLine = "";
        String response = chatSession.multisentenceRespond("Human: Hello\n");
        IOUtils.writeOutputTextLine("Robot", response);

        while(true) {
            textLine = IOUtils.readInputTextLine("Human");
            if(textLine == null || textLine.length() < 1) {
                textLine = MagicStrings.null_input;
            }

            if(textLine.equals("q")) {
                System.exit(0);
            } else if(textLine.equals("wq")) {
                bot.writeQuit();
                System.exit(0);
            } else {
                if(MagicBooleans.trace_mode) {
                    System.out.println("STATE=" + textLine + ":THAT=" + ((History)chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
                }

                response = chatSession.multisentenceRespond(textLine);
                while (response.contains("&lt;")) {
                    response = response.replace("&lt;", "<");
                }

                while(response.contains("&gt;")) {
                    response = response.replace("&gt;", ">");
                }

                IOUtils.writeOutputTextLine("Robot", response);
            }
        }
    }


}
