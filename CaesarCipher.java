package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/*
	@file CaesarCipher.java
	@author Judy Liu, Min Aung Zaw
	@version Java SE 17 May, 2024
*/

public class CaesarCipher extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/*
		Launch the application.
	*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CaesarCipher frame = new CaesarCipher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CaesarCipher() {
		
		
		//GUI main page
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Text Area for key value
		JTextArea keyInput = new JTextArea();
		keyInput.setBackground(new Color(220, 217, 225));
		keyInput.setBounds(10, 371, 412, 45);
		contentPane.add(keyInput);
		
		//Text Area for Input File
		JTextArea InputText = new JTextArea();
		InputText.setText("Input File");
		InputText.setBackground(new Color(220, 217, 225));
		InputText.setBounds(10, 215, 412, 45);
		contentPane.add(InputText);
		
		//Text Area for Output File
		JTextArea outputFileName = new JTextArea();
		outputFileName.setText("Output File");
		outputFileName.setBackground(new Color(220, 217, 225));
		outputFileName.setBounds(10, 294, 412, 45);
		contentPane.add(outputFileName);
		
		//Text Area for program final output 
		JTextArea EndOutput = new JTextArea();
		EndOutput.setBackground(new Color(220, 217, 225));
		EndOutput.setBounds(10, 48, 520, 157);
		contentPane.add(EndOutput);
		
	
		//Button for Key value
		JButton keybutton = new JButton("Key");
		keybutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
			}
		});
		keybutton.setForeground(new Color(255, 255, 255));
		keybutton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		keybutton.setBackground(new Color(0, 0, 0));
		keybutton.setBounds(432, 371, 98, 45);
		contentPane.add(keybutton);
		
		//Button for Input file
		JButton InputButton = new JButton("Input");
		InputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
			}
		});
		InputButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		InputButton.setBackground(new Color(0, 0, 0));
		InputButton.setForeground(new Color(255, 255, 255));
		InputButton.setBounds(432, 215, 98, 45);
		contentPane.add(InputButton);
		
		//Button for Output file
		JButton OutputButton = new JButton("Output");
		OutputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
			}
		});
		OutputButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		OutputButton.setForeground(new Color(255, 255, 255));
		OutputButton.setBackground(new Color(0, 0, 0));
		OutputButton.setBounds(432, 294, 98, 45);
		contentPane.add(OutputButton);
		
		
		//Button for Encryption Algorithm
		JButton Encrypt = new JButton("Encrypt");
		Encrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				//Encryption Algorithm
				Scanner sc = new Scanner(System.in);
				int shift;
				String keyConvert=keyInput.getText();
				shift= Integer.valueOf(keyConvert) ;	
				String inputFilePath = InputText.getText();
				String outputFilePath=outputFileName.getText();
				
				File inputFile = new File(inputFilePath);
		        StringBuilder ciphertext = new StringBuilder();

		        try (Scanner fileScanner = new Scanner(inputFile)) {
		            while (fileScanner.hasNextLine()) {
		                String plaintext = fileScanner.nextLine();
		                for (int i = 0; i < plaintext.length(); i++) {
		                    char alphabet = plaintext.charAt(i);
		                    if (alphabet >= 'a' && alphabet <= 'z') {
		                        // Shift lowercase letters
		                        alphabet = (char) (alphabet + shift);
		                        if (alphabet > 'z') {
		                            alphabet = (char) (alphabet - 26); // Wrap around the alphabet
		                        }
		                        ciphertext.append(alphabet);
		                    } else if (alphabet >= 'A' && alphabet <= 'Z') {
		                        // Shift uppercase letters
		                        alphabet = (char) (alphabet + shift);
		                        if (alphabet > 'Z') {
		                            alphabet = (char) (alphabet - 26); // Wrap around the alphabet
		                        }
		                        ciphertext.append(alphabet);
		                    } else {
		                        // Append non-alphabet characters unchanged
		                        ciphertext.append(alphabet);
		                    }
		                }
		                ciphertext.append(System.lineSeparator()); // Maintain the structure of the input file
		            }
		        } catch (FileNotFoundException eEncrypt) {
		            String erroEncrypt="Input file not found. Please check the path and try again.";
		            EndOutput.append(erroEncrypt+"\n");
		        	
		            return;
		        }

		        try (PrintWriter out = new PrintWriter(outputFilePath)) {
		            out.println(ciphertext.toString());
		            EndOutput.append("Encryption completed successfully. Check the output file."+"\n");
		        } catch (FileNotFoundException eEncrypt) {
		        
	                  EndOutput.append("Could not write to output file. Please check the path and try again."+"\n");
		        	
		        }
                  
                  //EndOutput.append("Encryption completed successfully. Check the output file."+"\n");
		        
		        sc.close();
			}
		});
		Encrypt.setBounds(10, 10, 108, 28);
		contentPane.add(Encrypt);
		
		
		//Button for Decryption Algorithm
		
		JButton Decrypt = new JButton("Decrypt");
		Decrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Encryption Algorithm
				Scanner sc = new Scanner(System.in);
				int shift;
				String keyConvert=keyInput.getText();
				shift= Integer.valueOf(keyConvert) ;	
				String inputFilePath = InputText.getText();
				String outputFilePath=outputFileName.getText();
				        File inputFile = new File(inputFilePath);
				        StringBuilder decryptedText = new StringBuilder();
						//tries to scan the input file
				        try (Scanner fileScanner = new Scanner(inputFile)) {
							//scan every line
				            while (fileScanner.hasNextLine()) {
				                String encryptedText = fileScanner.nextLine();
								//iterate through each letter, and condition statement of captial letters, lowercase letters, and special characters
				                for (int i = 0; i < encryptedText.length(); i++) {
				                    if (Character.isLowerCase(encryptedText.charAt(i))) {
										//decrypt algorithm: shifts the current letter by the shift, subtracts the value of a, and adds 26 for the alphabet. To account for the wrap, modulus 26
				                        int index = (encryptedText.charAt(i) - 'a' - shift + 26) % 26;
				                        if (index < 0) {
				                            index += 26;
				                        }
										//appends the character to the decryptText string
				                        decryptedText.append((char)('a' + index));
				                    } else if (Character.isUpperCase(encryptedText.charAt(i))) {
				                        int index = (encryptedText.charAt(i) - 'A' - shift + 26) % 26;
				                        if (index < 0) {
				                            index += 26;
				                        }
				                        decryptedText.append((char)('A' + index));
										//any special letters get filtered to here where we simply just append it
				                    } else {
				                        decryptedText.append(encryptedText.charAt(i));
				                    }
				                }
				                decryptedText.append(System.lineSeparator());
				                sc.close();
				            }
						//to catch any exception with the input file
				        } catch (FileNotFoundException ex) {
				            EndOutput.append("Input file not found. Please check the path and try again."+"\n");
				        }
						//tries to print the decrypt string into the output file
				        try (PrintWriter out = new PrintWriter(outputFilePath)) {
				            out.println(decryptedText);
				            EndOutput.append("Finished decrypting text. Check the output file."+"\n");
				        } catch (FileNotFoundException ex) { // cathes any exception
			                  EndOutput.append("Could not write to output file. Please check the path and try again."+"\n");
				        }
				    
			
		}
		});
		Decrypt.setBounds(128, 10, 108, 28);
		contentPane.add(Decrypt);
		
	
		
	}
}
