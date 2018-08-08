package client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.FrontController;
import controllerRequest.InputRequest;
import controllerRequest.SearchDataRequest;

public class Main 
{

    private JButton playGame, playerNames, exitGame;

    public Main()
    {

        JPanel mainCard = new JPanel();
        mainCard.setSize(100, 100);
        playGame = new JButton("Add Data");
        playerNames = new JButton("Search Data");
        exitGame = new JButton("Add Entity to Database");
        playGame.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        		  InputRequest request = new SearchDataRequest();
        		  FrontController.searchData(request);
        	  } 
        	} );


        mainCard.add(playGame, BorderLayout.NORTH);
        mainCard.add(playerNames, BorderLayout.CENTER);
        mainCard.add(exitGame, BorderLayout.SOUTH);

        JFrame window = new JFrame("MUM MANAGER");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(mainCard);
        window.setSize(400, 100);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
                
              
            }
        });
    }

}
