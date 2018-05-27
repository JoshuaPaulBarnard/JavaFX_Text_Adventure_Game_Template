/*
 *  Author:        Joshua Paul Barnard
 *  GitHub:        https://github.com/JoshuaPaulBarnard
 *  License:       MIT
 */



package JavaFX_Text_Adventure_Game_Template;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;



/**
 *
 * @author JoshuaPaulBarnard
 */
public class UI_Controller implements Initializable
{
    // Basic UI Handlers
    @FXML
    public Text current_HP;

    @FXML
    public Text max_HP;

    @FXML
    public Text current_WP;

    @FXML
    public Text max_WP;

    @FXML
    public Text gold_Current;

    @FXML
    public TextArea status_Display;

    @FXML
    public TextArea inventory_Display;

    @FXML
    public TextArea map_Log;

    @FXML
    public TextArea primary_Console;

    @FXML
    public TextField text_Input;

    @FXML
    public TextArea events_Log;

    @FXML
    public TextArea class_Log;

    @FXML
    public TextArea conversation_Log;

    @FXML
    public TextArea combat_Log;

    @FXML
    public TextArea extra_Log;







//-------------------------------------------Begin Input Event Handler-------------------------------------------------------

// Basic game control functions
    @FXML
    private void game_Exit(  )
    {
        Platform.exit();
        System.exit(0 );
    }


// Initialize Variables
    private int beginning_step = 1;
    private boolean isChangeName = false;
    private boolean isChangeGender = false;
    private boolean isChangeClass = false;


// Event handler for the user pressing enter or clicking the submitButton.

    @FXML
    public void inputTextHandler(ActionEvent event )
    {

// inputText is for passing the users text into the primary console, unmodified.
// checkText is converted to lowercase and used for checking for game commands.
        String inputText = "";
        String checkText = "";
        inputText = text_Input.getText();
        checkText = text_Input.getText().toLowerCase();



// Display users input as formatted text in primary console.
        primary_Console.appendText( playersName + ":  " + inputText + "\n" );



// Beginning Sequence
        if( isChangeName == true )
        {
            if( playersName.equals( "" ) && text_Input.getText().equals( "" ) )
            {
                primary_Console.appendText( "Error:  Please Re-enter Name\n" );
            }
            else
            {
                playersName = text_Input.getText();
                isChangeName = false;
                beginning_step = 3;
            }
        }

        if( isChangeGender == true )
        {
            if( checkText.contains( "female" ) )
            {
                isMale = false;
                isChangeGender = false;
                beginning_step = 4;
            }
            else if( checkText.contains( "male" ) )
            {
                isMale = true;
                isChangeGender = false;
                beginning_step = 4;
            }
        }

        if( isChangeClass == true )
        {
            if( checkText.contains( "bard" ) )
            {
                playersClass = "Bard";
                class_Log.setText( "You are a Bard." );
                isChangeClass = false;
                beginning_step = 5;
            }
            if( checkText.contains( "warrior" ) )
            {
                playersClass = "Warrior";
                class_Log.setText( "You are a Warrior." );
                isChangeClass = false;
                beginning_step = 5;
            }
            if( checkText.contains( "vagabond" ) )
            {
                playersClass = "Vagabond";
                class_Log.setText( "You are a Vagabond." );
                isChangeClass = false;
                beginning_step = 5;
            }
        }


// Clear TextField
        text_Input.clear();

// Check if it is the beginning sequence
        beginning_Sequence();

// Pass the users input text to functions which will check for keywords
        checkText_General( checkText );
        checkText_TextAdventure( checkText );
    }

    //-------------------------------------------End Input Event Handler-------------------------------------------------------



    public void beginning_Sequence(  )
    {
        // Intro Sequence
        if( beginning_step == 1 )
        {
            primary_Console.appendText(
                    "Welcome to my JavaFX Incremental Text Adventure Game Template!\n" +
                            "___________________________________________________________________\n" +
                            "\n"
            );

            beginning_step = 2;
        }

        // Name Enterance Sequence
        if( beginning_step == 2 )
        {
            isChangeName = true;

            primary_Console.appendText(
                    "\n" +
                            "Please Enter Your Name:\n" +
                            "\n"
            );
        }

        // Gender Choice Sequence
        if( beginning_step == 3 )
        {
            isChangeGender = true;

            primary_Console.appendText(
                    "\n" +
                            "What Gender Are you?\n" +
                            "Male\n" +
                            "Female\n" +
                            "\n"
            );
        }

        // Class Choice Sequence
        if( beginning_step == 4 )
        {
            isChangeClass = true;

            primary_Console.appendText(
                    "\n" +
                            "What is your class?\n" +
                            "Bard\n" +
                            "Warrior\n" +
                            "Vagabond\n" +
                            "\n"
            );
        }

        // Beginning start_Game Sequence
        if( beginning_step == 5 )
        {
            primary_Console.setText(
                            "______________________________________________________________________________________________\n" +
                            "                                                        Thank you for your choices!\n" +
                            "                                                              Enjoy The Game!!\n" +
                            "\n"
            );

            beginning_step = 6;
        }

        // Start the Game
        if( beginning_step == 6 )
        {
            start_Game();

            beginning_step = 0;
        }

        // Beginning position
        if( beginning_step > 0 )
        {
            position = "beginning";
        }
    }



//-----------------------------------------------------Start the Games Code--------------------------------------------------------------------------


    public void start_Game( )
    {
        map_Log.setText(
                "\n" +
                        "    Game Map:      " + "\n" +
                        "         _         " + "\n" +
                        "        |_|        " + "\n" +
                        "         |         " + "\n" +
                        " _      _     _    " + "\n" +
                        "|_| - |_| - |_|    " + "\n" +
                        "         |         " + "\n" +
                        "         _         " + "\n" +
                        "        |_|        " + "\n"
        );

        position = "townGate";

        playerSetup();
    }



    public String playersName = "";
    public boolean isMale = true;
    public String playersClass = "";
    public String playersColour = "";

    int currentHP, maxHP;
    int currentWP, maxWP;
    int currentGold;
    int monsterHP, silverRing;
    String weapon, position;


    public void playerSetup()
    {
        currentHP = 20;
        maxHP = 20;
        currentWP = 10;
        maxWP = 10;
        monsterHP = 20;
        currentGold = 10;


        String currentHP_String = Integer.toString( currentHP );
        current_HP.setText( currentHP_String);

        String maxHP_String = Integer.toString( maxHP );
        max_HP.setText( maxHP_String );

        String currentWP_String = Integer.toString( currentWP );
        current_WP.setText( currentWP_String);

        String maxWP_String = Integer.toString( maxWP );
        max_WP.setText( maxWP_String );

        String currentGold_String = Integer.toString( currentGold );
        gold_Current.setText( currentGold_String );


        events_Log.appendText
                (
                "Game Controls:\n" +
                        "Choose your option by typing: 1, 2, 3, 4, or 5\n" +
                        "Type Quit to exit the game.\n"
        );

        inventory_Display.appendText
                (
                "\n" +
                        "       Your Inventory:\n" +
                        "1:   Knife\n"
        );

        status_Display.setText
                (
                "\n" +
                        "       Your Information:\n" +
                        "Name:  " + playersName + "\n" +
                        "Health Points:  " + maxHP + "\n" +
                        "Will Power:  " + maxWP + "\n"
        );

        townGate();
    }
    
    
    public void townGate()
    {
        position = "townGate";
        primary_Console.appendText
        ( 
                "\n" +
                        "You are at the gate of the town.\n" +
                        "A guard is standing in front of you.\n" + "\n"+
                        "What do you do?\n" +
                        "1. Talk to the guard\n" +
                        "2. Attack the guard\n" +
                        "3. Leave\n"
         );
        extra_Log.setText
                (
                        "\n" +
                                "You are at the gate of the town.\n" +
                                "A guard is standing in front of you.\n" + "\n"+
                                "What will you do?\n" +
                                "1. Talk to the guard\n" +
                                "2. Attack the guard\n" +
                                "3. Leave\n"
                );
    }
    
    public void talkGuard()
    {
        position = "talkGuard";
        primary_Console.appendText(
                "\n" +
                "Guard:  Hello stranger. I have never seen your face.\n" +
                "Guard:  I'm sorry but we cannot let a stranger enter our town.\n"
        );

        townGate();
    }
    
    public void attackGuard()
    {
        position = "attackGuard";
        primary_Console.appendText(
                "\n" +
                        "Guard: Hey don't be stupid!\n" +"\n" +
                        "The guard fought back and hit you hard.\n" +
                        "(You receive 3 damage)\n"
        );

        combat_Log.appendText(
                "\n" +
                        "You Received 3 Damage from the Guard.\n"
        );

        currentHP = currentHP -3;
        String currentHP_String = Integer.toString( currentHP );
        current_HP.setText( currentHP_String);

        status_Display.setText
                (
                        "\n" +
                                "       Your Information:\n" +
                                "Name:  " + playersName + "\n" +
                                "Health Points:  " + currentHP + "\n" +
                                "Will Power:  " + maxWP + "\n"
                );

        townGate();
    }
    
    public void crossRoad()
    {
        position = "crossRoad";

        primary_Console.appendText(
                "\n" +
                        "You are at a crossroad.\n" + "\n" +
                        "There is a sign, it reads:\n" +
                        "If you go south, you will go back to the town.\n" + "\n" +
                        "What will you do?\n" +
                        "1.  Go north\n" +
                        "2.  Go east\n" +
                        "3.  Go south\n" +
                        "4.  Go west\n" +
                        "5.  Talk to the Old Man\n" +
                "\n"
        );

        extra_Log.setText(
                "\n" +
                        "You are at a crossroad.\n" + "\n" +
                        "There is a sign, it reads:\n" +
                        "If you go south, you will go back to the town.\n" + "\n" +
                        "What will you do?\n" +
                        "1.  Go north\n" +
                        "2.  Go east\n" +
                        "3.  Go south\n" +
                        "4.  Go west\n" +
                        "5.  Talk to the Old Man\n"
        );
    }

    public void oldMan()
    {
        position = "crossRoad";

        primary_Console.appendText(
                "\n" +
                        "You walk up to the old man standing by the sign.\n" +
                        "Old Man:  I've been trying to get into that town for over 10 years.\n" +
                        "Old Man:  I heard that strangers need some sort of a silver ring to get inside.\n" +
                        "\n"
        );

        conversation_Log.appendText(
                "\n" +
                        "You walk up to the old man standing by the sign.\n" +
                        "Old Man:  I've been trying to get into that town for over 10 years.\n" +
                        "Old Man:  I heard that strangers need some sort of a silver ring to get inside.\n"

        );
    }
    
    public void north()
    {
        position = "north";

        primary_Console.appendText(
                "\n" +
                        "You are at a river.\n" +
                        "You drink the water and rest at the riverside.\n" + "\n" +
                        "(Your HP is recovered by 1)\n" + "\n" +
                        "What Will you do?\n" +
                        "1.  Go south\n" +
                        "2.  Relax some more\n"
        );

        extra_Log.setText(
                "\n" +
                        "You are at a river.\n" +
                        "You drink the water and rest at the riverside.\n" + "\n" +
                        "(Your HP is recovered by 1)\n" + "\n" +
                        "What Will you do?\n" +
                        "1.  Go south\n" +
                        "2.  Relax some more\n"
        );

        events_Log.appendText(
                "\n" +
                        "You have recovered 1 Health Points from resting at the river.\n"
        );

        if( currentHP < maxHP )
        {
            currentHP = currentHP + 1;
        }

        String currentHP_String = Integer.toString( currentHP );
        current_HP.setText( currentHP_String);

        status_Display.setText
                (
                        "\n" +
                                "       Your Information:\n" +
                                "Name:  " + playersName + "\n" +
                                "Health Points:  " + currentHP + "\n" +
                                "Will Power:  " + maxWP + "\n"
                );
    }

    public void findFaerie()
    {
        position = "north";

        primary_Console.appendText(
                "\n" +
                        "While resting along the River a faerie comes floating down the river.\n" +
                        "The Faerie notices you and seems to like you.\n" +
                        "The Faerie starts dancing around your head, occassionally blinking in and out of sight.\n" +
                "\n"
        );

        events_Log.appendText(
                "\n" +
                        "While resting along the River a faerie comes floating down the river.\n" +
                        "The Faerie notices you and seems to like you.\n" +
                        "The Faerie starts dancing around your head, occassionally blinking in and out of sight.\n"
        );

    }
    
    public void east()
    {
        position = "east";

        weapon = "Long Sword";

        primary_Console.appendText(
                "\n" +
                        "You walked into a forest and found a Long Sword!\n" + "\n" +
                        "(You obtained a Long Sword)\n" + "\n" +
                        "What will you do?" + "\n" +
                        "1.  Go west\n"
        );

        extra_Log.setText(
                "\n" +
                        "You are in a forest.\n" + "\n" +
                        "What will you do?" + "\n" +
                        "1.  Go west\n"
        );

        events_Log.appendText(
                "\n" +
                        "You have Obtained a Long Sword.\n"
        );

        inventory_Display.appendText(
                "\n" +
                        "1:   Long Sword"
        );


    }
    
    public void west()
    {
        position = "west";

        primary_Console.appendText(
                "\n" +
                        "You encounter a goblin!\n" + "\n" +
                        "What will you do?\n" +
                        "1.  Fight\n" +
                        "2.  Run\n"
        );

        extra_Log.setText(
                "\n" +
                        "You encounter a goblin!\n" + "\n" +
                        "What will you do?\n" +
                        "1.  Fight\n" +
                        "2.  Run\n"
        );
    }
    
    public void fight()
    {
        position = "fight";

        primary_Console.appendText(
                "Monter HP: " + monsterHP + "\n" + "\n" +
                "What will you do?\n" +
                "1.  Attack\n" +
                "2.  Run\n"
        );

        combat_Log.appendText( "\n" + "Monter HP: " + monsterHP + "\n" );
    }
    
    public void playerAttack()
    {
        position = "playerAttack";

        int playerDamage = 0;

        if( weapon.equals( "Knife" ) )
        {
            playerDamage = new java.util.Random().nextInt(3);
        }
        else if( weapon.equals( "Long Sword" ) )
        {
            playerDamage = new java.util.Random().nextInt(12);
        }

        primary_Console.appendText( "You attacked the monster and dealt " + playerDamage + " damage!" );
        combat_Log.appendText( "You attacked the monster and dealt " + playerDamage + " damage!" );

        monsterHP = monsterHP - playerDamage;
    }

    public void monsterAttack()
    {
        position = "monsterAttack";

        int monsterDamage = 0;

        monsterDamage = new java.util.Random().nextInt(6);

        primary_Console.appendText( "The monster attacked you and dealt " + monsterDamage + " damage!");
        combat_Log.appendText( "The monster attacked you and dealt " + monsterDamage + " damage!");

        currentHP = currentHP - monsterDamage;

        String currentHP_String = Integer.toString( currentHP );
        current_HP.setText( currentHP_String);

        status_Display.setText
                (
                        "\n" +
                                "       Your Information:\n" +
                                "Name:  " + playersName + "\n" +
                                "Health Points:  " + currentHP + "\n" +
                                "Will Power:  " + maxWP + "\n"
                );
    }

    public void win()
    {
        position = "win";

        silverRing = 1;

        primary_Console.appendText(
                "\n" +
                        "You defeated the monster!\n" +
                        "The monster dropped a ring!\n" +
                        "(You obtained a Silver Ring)\n" + "\n" +
                        "What will you do?\n" +
                        "Go east\n"
        );

        combat_Log.appendText( "\n" + "You defeated the monster!\n" );
        events_Log.appendText( "\n" + "You Obtained a Silver Ring.\n" );
        inventory_Display.appendText(
                "\n" +
                        "1:   Silver Ring"
        );
    }

    public void lose()
    {
        position = "lose";

        primary_Console.appendText(
                "\n" +
                        "You are dead!\n" + "\n"
        );


    }

    public void ending()
    {
        position = "ending";

        primary_Console.appendText(
                "\n" +
                        "Guard:  Oh you killed that goblin!?\n" +
                        "Guard:  Thank you so much. You are true hero!\n" + "\n" +
                        "Guard:  Welcome to our town!\n" +
                        "\n"
        );

        conversation_Log.appendText(
                "\n" +
                        "Guard:  Oh you killed that goblin!?\n" +
                        "Guard:  Thank you so much. You are true hero!\n" +
                        "Guard:  Welcome to our town!\n"
        );

        extra_Log.setText(
                "\n" +
                        "Congratulations!!\n" +
                        "You made it into the town!\n"
        );
    }

//-----------------------------------------------------End the Games Code--------------------------------------------------------------------------


    //---------------------------------------------------- Start Text Checkers ------------------------------------------------------------------------------

    public void checkText_General( String checkText )
    {

        // Check input for game commands
        if( checkText.equals( "quit" ) )
        {
            game_Exit();
        }
    }



        public void checkText_TextAdventure( String yourChoice )
        {
            switch( position )
            {
                case "townGate":
                    if( yourChoice.equals( "1" ) )
                    {
                        if( silverRing == 1 )
                        {
                            ending();
                        }
                        else
                        {
                            talkGuard();
                        }
                    }
                    else if( yourChoice.equals( "2" ) )
                    {
                        attackGuard();
                    }
                    else if( yourChoice.equals( "3" ) )
                    {
                        crossRoad();
                    }
                    break;

                case "talkGuard":
                    if( yourChoice.equals( "1" ) )
                    {
                        townGate();
                    }
                    break;

                case "attackGuard":
                    if( yourChoice.equals( "1" ) )
                    {
                        townGate();
                    }
                    break;
                case "crossRoad":
                    if ( yourChoice.equals( "1" ) )
                    {
                        north();
                    }
                    else if( yourChoice.equals( "2" ) )
                    {
                        east();
                    }
                    else if( yourChoice.equals( "3" ) )
                    {
                        townGate();
                    }
                    else if( yourChoice.equals( "4" ) )
                    {
                        west();
                    }
                    else if( yourChoice.equals( "5" ) )
                    {
                        oldMan();
                    }
                    break;

                case "north":
                    if( yourChoice.equals( "1" ) )
                    {
                        crossRoad();
                    }
                    else if( yourChoice.equals( "2" ) )
                    {
                        findFaerie();
                    }
                    break;

                case "east":
                    if( yourChoice.equals( "1" ) )
                    {
                        crossRoad();
                    }
                    break;

                case "west":
                    if( yourChoice.equals( "1" ) )
                    {
                        fight();
                    }
                    else if( yourChoice.equals( "2" ) )
                    {
                        crossRoad();
                    }
                    break;

                case "fight":
                    if( yourChoice.equals( "1" ) )
                    {
                        playerAttack();
                    }
                    else if( yourChoice.equals( "2" ) )
                    {
                        crossRoad();
                    }
                    break;

                case "playerAttack":
                    if( yourChoice.equals( "1" ) )
                    {
                        if( monsterHP < 1 )
                        {
                            win();
                        }
                        else
                        {
                            monsterAttack();
                        }
                    }
                    break;

                case "monsterAttack":
                    if( yourChoice.equals( "1" ) )
                    {
                        if( currentHP < 1 )
                        {
                            lose();
                        }
                        else
                        {
                            fight();
                        }
                    }
                    break;

                case "win":
                    if( yourChoice.equals( "1" ) )
                    {
                        crossRoad();
                    }
                    break;

                case "beginning":
                    break;

            }
        }

    //----------------------------------------------------- End Text Checkers ------------------------------------------------------------------------------





    @Override
    public void initialize( URL url, ResourceBundle rb )
    {
        // TODO
        beginning_Sequence();
    }

}
