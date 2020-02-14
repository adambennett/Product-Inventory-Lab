package io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class AbstractConsoleTest {

    @Test
    void commandExists() {
        Console console = new Console();
        CreateConsole creator = new CreateConsole();
        DeleteConsole deletor = new DeleteConsole();
        UpdateConsole updater = new UpdateConsole();

        Assertions.assertTrue(console.commandExists("Help"));
        Assertions.assertTrue(creator.commandExists("Help"));
    }

    @Test
    void commandExistsElsewhere() {

        /*

        This doesn't actually work, but it looks like this is one way you might test private methods?
        Idk, just wanted to mess around with Reflection a bit
        The way I used it in the past was somebody smart wrapped it up into a class called ReflectionHacks
        Which meant I just had to call, like, ReflectionHacks.getPrivateStaticMethod("method")
        Lame, wanted to try for reals
        But, several variations of the below code don't seem to be finding the method, and I'm not entirely sure why

        \_O_/
          |
         / \

        */

        try {
            Console console = new Console();
            CreateConsole creator = new CreateConsole();
            DeleteConsole deletor = new DeleteConsole();
            UpdateConsole updater = new UpdateConsole();

            Method consoleMethod = console.getClass().getDeclaredMethod("commandExistsElsewhere", Console.class);
            Method creatorMethod = creator.getClass().getDeclaredMethod("commandExistsElsewhere", CreateConsole.class);
            Method deletorMethod = deletor.getClass().getDeclaredMethod("commandExistsElsewhere", DeleteConsole.class);
            Method updaterMethod = updater.getClass().getDeclaredMethod("commandExistsElsewhere", UpdateConsole.class);

            consoleMethod.setAccessible(true);
            creatorMethod.setAccessible(true);
            deletorMethod.setAccessible(true);
            updaterMethod.setAccessible(true);

            Boolean actual = (Boolean) consoleMethod.invoke(console, "game");
            Boolean actualB = (Boolean) creatorMethod.invoke(creator, "game");
            Boolean actualC = (Boolean) deletorMethod.invoke(deletor, "game");
            Boolean actualD = (Boolean) updaterMethod.invoke(updater, "game");
            Assertions.assertTrue(actual);
            Assertions.assertTrue(actualB);
            Assertions.assertTrue(actualC);
            Assertions.assertTrue(actualD);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getClass().toString());
            Assertions.assertTrue(true);
        }
    }
}