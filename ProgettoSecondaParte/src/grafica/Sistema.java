package grafica;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import progettoPart2.BeniDiConsumo;
import progettoPart2.Offerte;
import progettoPart2.Admin;
import progettoPart2.Cene;
import progettoPart2.PrestazioniOpera;
import progettoPart2.User;
import progettoPart2.Vacanze;

/**
 * 
 * @author GiovanniGenovese-AndreaValenti
 *
 */
public class Sistema extends JFrame {
	private static final long serialVersionUID = 1L;
	/**
	 * Costruttore contenente una classe interna per il login()
	 * Principale: Colore-Panello-areaAdmin-Titolo-AreaUtente-DimensioniFrame
	 */
	public Sistema() {
		class ChoiceListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		}
		new ChoiceListener();

		gold = new Color(0.9F,0.7F,0);
		setPanel();
		areaRiservata();
		setTitoloMain();
		areaUtente();
		setSize(800,800);
		setResizable(false);
	}


	//////////////////SYSTEM////////////////////////////////////////////////////////////
	/**
	 * Metodo che crea il panello principale
	 */
	public void setPanel() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		add(panel);
	}

	/**
	 * Metodo che disegna il titolo principale
	 */
	public void setTitoloMain() {
		JPanel panelTitle = new JPanel();
		panelTitle.setLayout(new BorderLayout());
		JLabel title = new JLabel("Welcome to");
		JLabel title2 = new JLabel("PrezziPazzi.com!");
		huge = new Font(("TimesRoman"), Font.ITALIC, 72);
		title.setFont(huge);
		title.setForeground(Color.BLUE);
		title2.setFont(huge);
		title2.setForeground(Color.BLUE);
		panelTitle.setBackground(gold);
		panelTitle.add(title,BorderLayout.NORTH);
		panelTitle.add(title2,BorderLayout.CENTER);
		panel.add(panelTitle,BorderLayout.NORTH);
	}

	/**
	 * Metodo che controlla se viene selezionato il pulsante radio admin, quindi se accede l'amministratore del sistema
	 */
	public void login() {
		if (admin.isSelected()) {
			panel.setVisible(false);
		}
	}
	/////////////////////////// SYSTEM - END /////////////////////////////////////////


	//////////////////////////// ADMIN ///////////////////////////////////////////////
	/**
	 * Metodo che crea l'area admin 
	 */
	public void areaRiservata() {
		JPanel panelLogAdmin = new JPanel();//Esterno
		JPanel radioButton = new JPanel();//Interno
		Font font = new Font("admin", Font.ITALIC, 17);
		//Layout - Border
		radioButton.setLayout(new BorderLayout());
		panelLogAdmin.setLayout(new GridLayout(1,1));
		panelLogAdmin.setBorder(new TitledBorder(new EtchedBorder(), "Area Riservata"));

		//radioButton - Label 
		admin = new JRadioButton("Admin");
		JLabel adminLabel = new JLabel("Sei un amministratore? Clicca qui --> ");

		//Listener Admin
		LogAdminListener log = new LogAdminListener();
		admin.addActionListener(log);

		//Background - Foreground - Font
		admin.setFont(font);
		adminLabel.setFont(font);
		admin.setBackground(Color.LIGHT_GRAY);
		admin.setForeground(Color.DARK_GRAY);
		adminLabel.setForeground(Color.DARK_GRAY);
		radioButton.setBackground(Color.LIGHT_GRAY);

		//Add panel
		radioButton.add(admin, BorderLayout.CENTER);
		radioButton.add(adminLabel,BorderLayout.WEST);
		panelLogAdmin.add(radioButton);
		panelLogAdmin.setBackground(Color.LIGHT_GRAY);
		panel.add(panelLogAdmin,BorderLayout.SOUTH);
	}

	//Ascoltatore per il Login dell'Admin
	class LogAdminListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			panel.setVisible(false);
			setVisible(false);
			accessoAdmin();
			panelSystem = new JPanel();
			menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			JMenu fileMenu = new JMenu("File");
			fileMenu.add(item("Visualizza Offerte"));
			fileMenu.add(item("Inserisci Offerte"));
			fileMenu.add(item("Rimuovi Offerte"));
			fileMenu.add(item("Politica Sconti"));
			fileMenu.add(item("Esci"));
			fileMenu.setFont(huge);
			fileMenu.setForeground(Color.BLUE);
			menuBar.add(fileMenu);
			menuBar.setBackground(gold);
			listaOff();
			addOfferte();
			try {
				rmvOfferte();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			politicaSconti();
			add(panelSystem);
		}
	}

	/**
	 * Metodo che attiva soltanto il pannello passato come stringa in input. 
	 * @param messaggio
	 * @return JMenuItemSelected
	 */
	public JMenuItem item (final String messaggio) {
		JMenuItem item = new JMenuItem(messaggio);
		huge = new Font("file", Font.ITALIC, 18);

		item.setFont(huge);
		item.setForeground(Color.BLUE);

		//////////////////CONTROL PANEL //////////////////////////////////
		class addItem implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (messaggio.equals("Visualizza Offerte")) {
					panelSystem.setVisible(true);
					listaOff.setVisible(true);
					insert.setVisible(false);
					rimuoviOff.setVisible(false);
					sconti.setVisible(false);
				}
				if(messaggio.equals("Inserisci Offerte")){
					panelSystem.setVisible(true);
					menuBar.setVisible(false);
					listaOff.setVisible(false);
					insert.setVisible(true);
					rimuoviOff.setVisible(false);
					sconti.setVisible(false);	
				}
				if(messaggio.equals("Rimuovi Offerte")) {
					panelSystem.setVisible(true);
					listaOff.setVisible(false);
					insert.setVisible(false);
					rimuoviOff.setVisible(true);
					sconti.setVisible(false);
				}
				if(messaggio.equals("Politica Sconti")) {
					panelSystem.setVisible(true);
					listaOff.setVisible(false);
					insert.setVisible(false);
					rimuoviOff.setVisible(false);
					sconti.setVisible(true);
				}
				if(messaggio.equals("Esci")) {
					JLabel msg = new JLabel("Arrivederci!");
					msg.setFont(huge);
					msg.setForeground(Color.BLUE);
					JOptionPane.showMessageDialog(null,msg, "Logout",JOptionPane.INFORMATION_MESSAGE);
					panelSystem.setVisible(false);
					menuBar.setVisible(false);
					panel.setVisible(true);
				}
			}
		} 
		ActionListener listener = new addItem();
		item.addActionListener(listener);
		return item;
	}
	//////////////////////////////////////////////////////////////////////

	public void accessoAdmin() {
		frame = new JFrame();
		logAdmin = new JPanel();//Principale
		JPanel panelArea = new JPanel(); //Esterno
		JPanel panelName = new JPanel(); //User
		JPanel panelPassword = new JPanel();//Password
		JPanel panelButton = new JPanel(); //Button

		huge = new Font ("user", Font.ITALIC + Font.BOLD, 12 );
		Font font = new Font ("button", Font.ITALIC, 14);

		//Button
		accediAdmin = new JButton("Accedi");
		accediAdmin.setBackground(Color.LIGHT_GRAY);
		accediAdmin.setFont(font);
		accediAdmin.setForeground(Color.BLUE);
		indietro = new JButton("Indietro");
		indietro.setBackground(Color.LIGHT_GRAY);
		indietro.setFont(font);
		indietro.setForeground(Color.BLUE);
		panelButton.add(accediAdmin);
		panelButton.add(indietro);

		//Label - Field
		JLabel nomeLabel = new JLabel("Username");
		final JTextField nomeText = new JTextField(15);
		nomeText.setFont(huge);
		nomeText.setForeground(Color.DARK_GRAY);
		JLabel cognomeLabel = new JLabel("Password");
		final JPasswordField cognomeText = new JPasswordField (15);
		cognomeText.setForeground(Color.DARK_GRAY);

		//Layout
		logAdmin.setLayout(new BorderLayout());
		panelArea.setLayout(new GridLayout(4,2));
		panelArea.setBorder(new EmptyBorder(50,-300,0,0));

		titleAdmin();

		////////////////////CONTROLLO ACCESSO ADMIN ///////////////////////////
		class logAdmin implements ActionListener {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				File file = new File("admin.txt");
				String name=nomeText.getText();
				String pass=cognomeText.getText();
				int log=0;

				try {
					a = new Admin();
					log = a.logIn(file, name, pass);
					if(log == -1) {
						JLabel msg = new JLabel("Username/Password non corretti");
						msg.setFont(huge);
						msg.setForeground(Color.BLUE);
						JOptionPane.showMessageDialog(null,msg, "Accesso Negato",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JLabel msg = new JLabel("Benvenuto " + name + "!");
						msg.setFont(huge);
						msg.setForeground(Color.BLUE);
						JOptionPane.showMessageDialog(null,msg, "Accesso Consentito",JOptionPane.INFORMATION_MESSAGE);
						frame.setVisible(false);
						setVisible(true);
					}
				} catch (ClassNotFoundException| IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		////////////////////////////////////////////////////////////////////////
		huge = new Font(("TimesRoman"), Font.ITALIC+Font.BOLD, 25);

		//Listener button
		logAdmin logAdminListener = new logAdmin();
		accediAdmin.addActionListener(logAdminListener);
		indietroListener main = new indietroListener();
		indietro.addActionListener(main);

		//Colors
		nomeLabel.setFont(huge);
		cognomeLabel.setFont(huge);
		nomeLabel.setForeground(Color.BLUE);
		cognomeLabel.setForeground(Color.BLUE);
		panelButton.setBackground(Color.LIGHT_GRAY);
		panelName.setBackground(Color.LIGHT_GRAY);
		panelPassword.setBackground(Color.LIGHT_GRAY);
		panelArea.setBackground(Color.LIGHT_GRAY);

		//Panel
		panelName.add(nomeLabel);
		panelName.add(nomeText);
		panelPassword.add(cognomeLabel);
		panelPassword.add(cognomeText);
		panelArea.add(panelName);
		panelArea.add(panelPassword);
		panelArea.add(panelButton);
		logAdmin.add(panelArea,BorderLayout.CENTER);

		//Frame Area Riservata
		frame.setUndecorated(true);
		frame.setSize(665,700);
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ((screenSize.width/2)-(frame.getWidth( )/2) , (screenSize.height/2)-(frame.getHeight( )/2));
		frame.add(logAdmin);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	/**
	 * Metodo che legge dal catalogo le offerte e le stampa in una JTextArea.
	 */
	public void listaOff() {
		try {
			a.sort();
		} 
		catch (Exception e2) {
			try {
				a = new Admin();
			} catch (ClassNotFoundException| IOException e) {
				e.printStackTrace();
			}
		}

		list = new Font("list", Font.ITALIC, 16);
		listaOff = new JPanel();
		listaOff.setVisible(false);

		ObjectInputStream input = null;
		try {		
			input = new ObjectInputStream(new FileInputStream("catalogo.dat"));
		} catch (FileNotFoundException e1) {	
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Offerte off = null;
		JTextArea text = new JTextArea(32,43);

		for(;;) {
			try {
				off = (Offerte)input.readObject();
				text.append("\n" + off);
				text.append("\n");
			}
			catch(Exception e){
				break;
			}
		}

		text.setFont(list);
		text.setForeground(Color.BLUE);
		text.setBackground(Color.LIGHT_GRAY);
		listaOff.add(text);
		scrol = new JScrollPane(text);
		listaOff.add(scrol);
		panelSystem.add(listaOff);
	}

	/**
	 * Metodo che aggiunge al pannello l'offerta selezionata e consente di aggiungere l'offerta al catalogo
	 */
	public void addOfferte() {
		class selectType implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				String offerta = (String) offerte.getSelectedItem();
				list = new Font("prova", Font.ITALIC, 15);

				////////////// CENA ////////////////////////////////////////
				if (offerta.equals("Cena")) {
					insert.setVisible(false);
					tipoOfferta = new JPanel();
					scelta = new JPanel();
					scelta.setBorder(new EmptyBorder(5,200,5,5));
					tipoOfferta.setLayout(new GridLayout(8,16));
					JLabel ristorante = new JLabel("Ristorante");
					final JTextField ristoranteText = new JTextField(20);
					ristorante.setFont(huge);
					ristorante.setForeground(Color.BLUE);
					ristoranteText.setFont(list);
					ristoranteText.setForeground(gold);
					JLabel luogo = new JLabel("Luogo");
					final JTextField luogoText = new JTextField(20);
					luogo.setFont(huge);
					luogo.setForeground(Color.BLUE);
					luogoText.setFont(list);
					luogoText.setForeground(gold);
					JLabel descrizione = new JLabel("Descrizione");
					final JTextField descrizioneText = new JTextField(20);
					descrizione.setFont(huge);
					descrizione.setForeground(Color.BLUE);
					descrizioneText.setFont(list);
					descrizioneText.setForeground(gold);JLabel costo = new JLabel("€");
					final JTextField costoText = new JTextField(20);
					costo.setFont(huge);
					costo.setForeground(Color.BLUE);
					costoText.setFont(list);
					costoText.setForeground(gold);
					JLabel scadenza = new JLabel("Scadenza");
					scadenza.setFont(huge);
					scadenza.setForeground(Color.BLUE);
					JLabel quantita = new JLabel("Quantità");
					final JTextField quantitaText = new JTextField(20);
					quantita.setFont(huge);
					quantita.setForeground(Color.BLUE);
					quantitaText.setFont(list);
					quantitaText.setForeground(gold);
					JButton ok = new JButton("Salva");
					JButton annulla = new JButton("Annulla");
					tipoOfferta.add(ristorante);
					tipoOfferta.add(ristoranteText);
					tipoOfferta.add(luogo);
					tipoOfferta.add(luogoText);
					tipoOfferta.add(descrizione);
					tipoOfferta.add(descrizioneText);
					tipoOfferta.add(quantita);
					tipoOfferta.add(quantitaText);
					tipoOfferta.add(costo);
					tipoOfferta.add(costoText);
					tipoOfferta.add(scadenza);
					tipoOfferta.add(data());
					scelta.add(ok);
					scelta.add(annulla);
					panelSystem.add(tipoOfferta, BorderLayout.CENTER);
					panelSystem.add(scelta, BorderLayout.SOUTH);

					//Ascoltatore del pulsante Salva
					class cenaListener implements ActionListener {
						public void actionPerformed(ActionEvent e) {
							try {
							String ristorante = ristoranteText.getText();
							String luogo = luogoText.getText();
							String descrizione = descrizioneText.getText();
							double costo = Double.parseDouble(costoText.getText());
							int day = (int) giorno.getSelectedItem();
							int month = (int) mese.getSelectedItem();
							int year = (int) anno.getSelectedItem();
							int quantita = Integer.parseInt(quantitaText.getText());
							a.addProdotto(new Cene(a.getId(),costo, ristorante,descrizione,luogo,new GregorianCalendar(year,month-1,day), quantita));
							}
							catch (Exception e2) {
								JOptionPane.showMessageDialog(null, e2.getMessage());
							}
							try {
								tipoOfferta.setVisible(false);
								scelta.setVisible(false);
								menuBar.setVisible(true);
								a.salva();
								listaOff();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							panelSystem.setVisible(false);
						}
					}
					ActionListener listenerOk = new cenaListener();
					ok.addActionListener(listenerOk);

					//Ascoltatore del pulsante Annulla
					class annullaListener implements ActionListener {
						public void actionPerformed(ActionEvent e) {
							tipoOfferta.setVisible(false);
							scelta.setVisible(false);
							panelSystem.setVisible(false);
							menuBar.setVisible(true);
						}
					}
					ActionListener listenerAnnulla = new annullaListener();
					annulla.addActionListener(listenerAnnulla);
				}	
				///////////////////////////////////////////////////////////////

				//////////////////////// VACANZE /////////////////////////////
				if (offerta.equals("Vacanza")) {

					insert.setVisible(false);
					tipoOfferta = new JPanel();
					scelta = new JPanel();
					scelta.setBorder(new EmptyBorder(5,200,5,5));
					tipoOfferta.setLayout(new GridLayout(7,8));
					JLabel destinazione = new JLabel("Destinazione");
					final JTextField destinazioneText = new JTextField(15);
					destinazione.setFont(huge);
					destinazione.setForeground(Color.BLUE);
					destinazioneText.setFont(list);
					destinazioneText.setForeground(gold);JLabel costo = new JLabel("Prezzo");
					final JTextField costoText = new JTextField(15);
					costo.setFont(huge);
					costo.setForeground(Color.BLUE);
					costoText.setFont(list);
					costoText.setForeground(gold);JLabel partenza = new JLabel("Partenza");
					partenza.setForeground(Color.BLUE);
					JLabel scadenza = new JLabel("Scadenza");
					partenza.setFont(huge);
					scadenza.setFont(huge);
					scadenza.setForeground(Color.BLUE);
					JButton ok = new JButton("Salva");
					JButton annulla = new JButton("Annulla");
					tipoOfferta.add(destinazione);
					tipoOfferta.add(destinazioneText);
					tipoOfferta.add(costo);
					tipoOfferta.add(costoText);
					tipoOfferta.add(partenza);
					tipoOfferta.add(data());
					tipoOfferta.add(scadenza);
					tipoOfferta.add(data2());
					scelta.add(ok);
					scelta.add(annulla);
					panelSystem.add(tipoOfferta, BorderLayout.CENTER);
					panelSystem.add(scelta,BorderLayout.SOUTH);

					class vacanzaListener implements ActionListener {
						public void actionPerformed(ActionEvent e) {
							String destinazione = destinazioneText.getText();
							double costo = Double.parseDouble(costoText.getText());
							int dayP = (int) giorno.getSelectedItem();
							int monthP = (int) mese.getSelectedItem();
							int yearP = (int) anno.getSelectedItem();
							int dayS = (int) giornoS.getSelectedItem();
							int monthS =(int) meseS.getSelectedItem();
							int yearS = (int) annoS.getSelectedItem();	
							a.addProdotto(new Vacanze(a.getId(),costo,destinazione,new GregorianCalendar(yearP,monthP-1,dayP), new GregorianCalendar(yearS,monthS-1, dayS)));

							try {
								a.salva();
								tipoOfferta.setVisible(false);
								scelta.setVisible(false);
								listaOff();
								menuBar.setVisible(true);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							panelSystem.setVisible(false);
						}
					}
					ActionListener listenerOk = new vacanzaListener();
					ok.addActionListener(listenerOk);

					class annullaListener implements ActionListener {
						public void actionPerformed(ActionEvent e) {
							tipoOfferta.setVisible(false);
							scelta.setVisible(false);
							panelSystem.setVisible(false);
							menuBar.setVisible(true);
						}
					}
					ActionListener listenerAnnulla = new annullaListener();
					annulla.addActionListener(listenerAnnulla);
				}
				//////////////////////////////////////////////////////////////

				///////////// BENE DI CONSUMO ///////////////////////////////
				if (offerta.equals("Bene di consumo")) {
					insert.setVisible(false);
					tipoOfferta = new JPanel();
					scelta = new JPanel();
					scelta.setBorder(new EmptyBorder(5,200,5,5));
					tipoOfferta.setLayout(new GridLayout(8,16));

					JLabel nome = new JLabel ("Nome");
					final JTextField nomeText = new JTextField(20);
					nome.setFont(huge);
					nome.setForeground(Color.BLUE);
					nomeText.setFont(list);
					nomeText.setForeground(gold);
					JLabel prezzo = new JLabel ("Prezzo");
					final JTextField prezzoText = new JTextField(20);
					prezzo.setFont(huge);
					prezzo.setForeground(Color.BLUE);
					prezzoText.setFont(list);
					prezzoText.setForeground(gold);
					JLabel quantita = new JLabel ("Quantita'");
					final JTextField quantitaText = new JTextField(20);
					quantita.setFont(huge);
					quantita.setForeground(Color.BLUE);
					quantitaText.setFont(list);
					quantitaText.setForeground(gold);
					JLabel fornitore = new JLabel ("Fornitore"); 
					final JTextField fornitoreText = new JTextField(20);
					fornitore.setFont(huge);
					fornitore.setForeground(Color.BLUE);
					fornitoreText.setFont(list);
					fornitoreText.setForeground(gold);
					JLabel giudizio = new JLabel ("Feedback");
					final JTextField giudizioText = new JTextField(20);
					giudizio.setFont(huge);
					giudizio.setForeground(Color.BLUE);
					giudizioText.setFont(list);
					giudizioText.setForeground(gold);
					JButton ok = new JButton("Salva");
					JButton annulla = new JButton("Annulla");

					tipoOfferta.add(nome);
					tipoOfferta.add(nomeText);
					tipoOfferta.add(prezzo);
					tipoOfferta.add(prezzoText);
					tipoOfferta.add(quantita);
					tipoOfferta.add(quantitaText);
					tipoOfferta.add(fornitore);
					tipoOfferta.add(fornitoreText);
					tipoOfferta.add(giudizio);
					tipoOfferta.add(giudizio());
					scelta.add(ok);
					scelta.add(annulla);
					panelSystem.add(tipoOfferta,BorderLayout.CENTER);
					panelSystem.add(scelta,BorderLayout.SOUTH);

					class beneDiConsumoListener implements ActionListener {
						public void actionPerformed(ActionEvent e) {
							String nome = nomeText.getText();
							double prezzo = Double.parseDouble(prezzoText.getText());
							int quantita = Integer.parseInt(quantitaText.getText());
							String fornitore = fornitoreText.getText();
							int giudizio = (int) voto.getSelectedItem();
							a.addProdotto(new BeniDiConsumo(a.getId(),prezzo,nome,quantita,fornitore,giudizio));
							try {
								a.salva();
								tipoOfferta.setVisible(false);
								scelta.setVisible(false);
								listaOff();
								menuBar.setVisible(true);

							} catch (Exception e1) {
								e1.printStackTrace();
							}
							panelSystem.setVisible(false);
						}
					}
					ActionListener listenerOk = new beneDiConsumoListener();
					ok.addActionListener(listenerOk);

					class annullaListener implements ActionListener {
						public void actionPerformed(ActionEvent e) {
							tipoOfferta.setVisible(false);
							scelta.setVisible(false);
							menuBar.setVisible(true);
							panelSystem.setVisible(false);
						}
					}
					ActionListener listenerAnnulla = new annullaListener();
					annulla.addActionListener(listenerAnnulla);
				}
				////////////////////////////////////////////////////////////////

				////////////// Prestazione d'opera ////////////////////////////
				if (offerta.equals("Prestazione d'opera")) {
					insert.setVisible(false);
					tipoOfferta = new JPanel();
					scelta = new JPanel();
					scelta.setBorder(new EmptyBorder(5,200,5,5));
					tipoOfferta.setLayout(new GridLayout(8,16));

					JLabel luogo = new JLabel ("Luogo");
					final JTextField luogoText = new JTextField(20);
					luogo.setFont(huge);
					luogo.setForeground(Color.BLUE);
					luogoText.setFont(list);
					luogoText.setForeground(gold);
					JLabel descrizione = new JLabel ("Descrizione");
					final JTextField descrizioneText = new JTextField(20);
					descrizione.setFont(huge);
					descrizione.setForeground(Color.BLUE);
					descrizioneText.setFont(list);
					descrizioneText.setForeground(gold);
					JLabel fornitore = new JLabel("Fornitore");
					final JTextField fornitoreText = new JTextField(20);
					fornitore.setFont(huge);
					fornitore.setForeground(Color.BLUE);
					fornitoreText.setFont(list);
					fornitoreText.setForeground(gold);
					JLabel giudizio = new JLabel("Feedback");
					giudizio.setFont(huge);
					giudizio.setForeground(Color.BLUE);
					JLabel prezzo = new JLabel ("Prezzo");
					prezzo.setFont(huge);
					prezzo.setForeground(Color.BLUE);
					final JTextField prezzoText = new JTextField(20);
					prezzoText.setFont(list);
					prezzoText.setForeground(gold);

					JButton ok = new JButton("Salva");
					JButton annulla = new JButton("Annulla");

					tipoOfferta.add(luogo);
					tipoOfferta.add(luogoText);
					tipoOfferta.add(descrizione);
					tipoOfferta.add(descrizioneText);
					tipoOfferta.add(fornitore);
					tipoOfferta.add(fornitoreText);
					tipoOfferta.add(prezzo);
					tipoOfferta.add(prezzoText);
					tipoOfferta.add(giudizio);
					tipoOfferta.add(giudizio());
					scelta.add(ok);
					scelta.add(annulla);

					panelSystem.add(tipoOfferta, BorderLayout.CENTER);
					panelSystem.add(scelta, BorderLayout.SOUTH);

					class prestazioneDOperaListener implements ActionListener {
						public void actionPerformed(ActionEvent e) {
							String luogo = luogoText.getText();
							String descrizione = descrizioneText.getText();
							String fornitore = fornitoreText.getText();
							int giudizio = (int) voto.getSelectedItem();
							double prezzo = Double.parseDouble(prezzoText.getText());

							a.addProdotto(new PrestazioniOpera(a.getId(),prezzo,luogo,descrizione,fornitore,giudizio));
							try {
								a.salva();
								tipoOfferta.setVisible(false);
								scelta.setVisible(false);
								listaOff();
								menuBar.setVisible(true);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							panelSystem.setVisible(false);
						}
					}

					ActionListener listenerOk = new prestazioneDOperaListener();
					ok.addActionListener(listenerOk);

					class annullaListener implements ActionListener {
						public void actionPerformed(ActionEvent e) {
							tipoOfferta.setVisible(false);
							scelta.setVisible(false);
							panelSystem.setVisible(false);
							menuBar.setVisible(true);
						}
					}
					ActionListener listenerAnnulla = new annullaListener();
					annulla.addActionListener(listenerAnnulla);
				}				
				//////////////////////////////////////////////////////
			}
		}

		ActionListener listener = new selectType();
		insert = new JPanel();
		insert.setVisible(false);
		insert.setLayout(new GridLayout(1,1));
		insert.setBorder(new EmptyBorder(5,5,5,5));
		JLabel tipo = new JLabel("Tipologia: ");
		list = new Font("prova", Font.ITALIC, 22);
		tipo.setFont(list);
		tipo.setForeground(Color.BLUE);
		offerte = new JComboBox<String>();
		offerte.setFont(list);
		offerte.setForeground(gold);
		offerte.addItem("Cena");
		offerte.addItem("Vacanza");
		offerte.addItem("Bene di consumo");
		offerte.addItem("Prestazione d'opera");	
		offerte.addActionListener(listener);
		insert.add(tipo);
		insert.add(offerte);
		panelSystem.add(insert, BorderLayout.NORTH);
	}

	/**
	 * Metodo che genera i giorni da 1 a 31, mesi da 1 a 12, anni dal 2014 al 2022, li aggiunge ad un pannello 
	 * e li restituisce
	 * @return panelData
	 */
	public JPanel data() {
		giorno = new JComboBox<Integer>();
		mese = new JComboBox<Integer>();
		anno = new JComboBox<Integer>();
		JPanel panelData = new JPanel();

		for (int i = 1; i<32; i++) {
			giorno.addItem(i);
			if (i < 13) {
				mese.addItem(i);
			}
		}
		for (int i = 2014; i<2023; i++) {
			anno.addItem(i);
		}
		panelData.add(giorno);
		panelData.add(mese);
		panelData.add(anno);
		return panelData;
	}

	/**
	 * Metodo che genera i giorni da 1 a 31, mesi da 1 a 12, anni dal 2014 al 2022, li aggiunge ad un pannello 
	 * e li restituisce
	 * @return panelData
	 */
	public JPanel data2() {
		giornoS = new JComboBox<Integer>();
		meseS = new JComboBox<Integer>();
		annoS = new JComboBox<Integer>();
		JPanel panelData = new JPanel();
		for (int i = 1; i<32; i++) {
			giornoS.addItem(i);
			if (i < 13) {
				meseS.addItem(i);
			}
		}
		for (int i = 2014; i<2023; i++) {
			annoS.addItem(i);
		}

		panelData.add(giornoS);
		panelData.add(meseS);
		panelData.add(annoS);
		return panelData;
	}

	/**
	 * Metodo che consente di inserire un giudizio sul fornitore da 1 a 5, aggiunge al pannello e lo ritorna
	 * @return panelVoto
	 */
	public JPanel giudizio() {
		JPanel panelVoto = new JPanel();
		voto = new JComboBox<Integer>();

		for (int i = 1 ; i < 6 ; i++) {
			voto.addItem(i);
		}

		panelVoto.add(voto);
		return panelVoto;
	}

	/**
	 * Metodo che consente di rimuovere le offerte dal catalogo, passando l'id dell'offerta
	 * @throws EOFException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws Exception
	 */
	public void rmvOfferte() throws EOFException, FileNotFoundException, ClassNotFoundException, IOException, Exception {
		rimuoviOff = new JPanel();
		JPanel okPanel = new JPanel();
		rimuoviOff.setLayout(new BorderLayout());
		rimuoviOff.setVisible(false);
		rimuoviOff.setBorder(new EmptyBorder(5,5,5,5));
		JLabel idLabel = new JLabel("Inserire id dell'offerta da rimuovere ");
		list = new Font("prova", Font.ITALIC + Font.BOLD, 20);
		idLabel.setFont(list);
		idLabel.setForeground(Color.BLUE);
		final JTextField text = new JTextField(2);
		text.setFont(list);
		text.setForeground(gold);
		JButton ok = new JButton("OK");
		Font font = new Font ("button", Font.ITALIC + Font.BOLD, 14);

		ok.setFont(font);
		ok.setForeground(Color.BLUE);
		ok.setBackground(Color.LIGHT_GRAY);
		//Ascoltatore pulsante ok
		class button implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					final int id = Integer.parseInt(text.getText());
					a.rmvProdotto(id);
					rimuoviOff.setVisible(false);
					a.salva();
					listaOff();
				} catch (Exception e1) {	
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		}

		button listener = new button();
		ok.addActionListener(listener);

		rimuoviOff.add(idLabel,BorderLayout.WEST);
		rimuoviOff.add(text,BorderLayout.CENTER);
		okPanel.add(ok);
		rimuoviOff.add(okPanel,BorderLayout.SOUTH);
		a.salva();
		listaOff();
		panelSystem.add(rimuoviOff,BorderLayout.CENTER);

	}

	/**
	 * Metodo che consente di applicare sconti sulle offerte
	 */
	public void politicaSconti() {
		sconti = new JPanel();
		sconti.setVisible(false);
		Font font = new Font ("button", Font.ITALIC + Font.BOLD, 14);
		JPanel panelArea = new JPanel();
		JPanel panelButton = new JPanel();
		sconti.setLayout(new BorderLayout());
		panelArea.setLayout(new BorderLayout());
		JTextArea area = new JTextArea(19,25);
		area.append("Generale: \n 1. Acquisto maggiore di due o più prodotti" + "\n");
		area.append("\nSconto Cena: \n 1.Cena nell'ultima settimana di validita' \n 2.il numero di cene ancora disponibili è maggiore della meta' delle cene iniziali da vendere\n");
		area.append("\nSconto Vacanza:\n 1.Vacanza nell'ultima settimana di validità\n");
		area.setEditable(false);
		area.setFont(list);
		area.setForeground(Color.BLUE);
		area.setBackground(Color.LIGHT_GRAY);
		JLabel label = new JLabel ("Applicare la politica sconti?");
		JButton si = new JButton ("SI");
		JButton no = new JButton ("NO");
		label.setFont(font);
		label.setForeground(Color.BLUE);
		si.setFont(font);
		si.setForeground(Color.BLUE);
		no.setForeground(Color.BLUE);
		no.setFont(font);

		//Ascoltatore del pulsante si 
		class siListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Saldi applicati ", "Politica Sconti",JOptionPane.INFORMATION_MESSAGE);
				try {
					a.prodottiScontati();
					sconti.setVisible(false);
					panelSystem.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}	
		}
		ActionListener listener = new siListener();
		si.addActionListener(listener);

		//Ascoltatore del pulsante no
		class noListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				sconti.setVisible(false);
				panelSystem.setVisible(false);
			}

		}
		ActionListener listenerNo = new noListener();
		no.addActionListener(listenerNo);

		panelArea.add(area,BorderLayout.NORTH);
		JScrollPane text = new JScrollPane(area);
		panelArea.add(text,BorderLayout.CENTER);
		panelButton.add(label);
		panelButton.add(si);
		panelButton.add(no);

		sconti.add(panelArea,BorderLayout.CENTER);
		sconti.add(panelButton,BorderLayout.SOUTH);
		panelSystem.add(sconti);


	}

	/**
	 * Metodo che disegna il titolo nell'area di accesso per l'Admin
	 */
	public void titleAdmin() {
		JLabel titoloReg = new JLabel("Area Riservata");
		JPanel panelTitoloReg = new JPanel();
		huge = new Font(("Calibra"), Font.ITALIC + Font.BOLD, 65);
		titoloReg.setFont(huge);
		titoloReg.setForeground(Color.BLUE);
		panelTitoloReg.add(titoloReg);
		panelTitoloReg.setBackground(gold);
		logAdmin.add(panelTitoloReg,BorderLayout.NORTH);
	}

	/**
	 * classe interna
	 * Consente di tornare al passo precedente, quando si clicca il pulsante indietro
	 *
	 */
	class indietroListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			panel.setVisible(true);
			setVisible(true);
			frame.setVisible(false);
			menuBar.setVisible(false);
		}
	}
	/////////////////////////////// ADMIN - END ///////////////////////////////////////

	/////////////////////////////////USER//////////////////////////////////////////////
	public void areaUtente() {
		JPanel panelUser = new JPanel();//Esterno
		JPanel panelArea = new JPanel();//Bordo
		JPanel panelId = new JPanel();//Username
		JPanel panelPass = new JPanel();//Password
		JPanel panelButton = new JPanel(); //Button

		huge = new Font(("TimesNewRoman"), Font.ITALIC, 25);
		final Font textFont = new Font("testo user", Font.ITALIC+Font.BOLD, 12);
		//Label e Field
		JLabel username = new JLabel("Username ");
		JLabel password = new JLabel("Password  ");
		final JTextField text = new JTextField(15);////////////
		final JPasswordField pass = new JPasswordField(15);/////////
		text.setFont(textFont);
		text.setForeground(Color.DARK_GRAY);
		pass.setForeground(gold);
		pass.setFont(textFont);
		class LogUserListener implements ActionListener {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					String username = text.getText();
					String codice=pass.getText();
					u=new User("userLogin.txt");
					int registrazione = u.login("si",username,codice);
					if(registrazione == 1) {
						JLabel msg = new JLabel("Benvenuto "+username +" in PrezziPazzi.com ");
						msg.setFont(textFont);
						msg.setForeground(Color.BLUE);
						JOptionPane.showMessageDialog(null,msg, "Accesso Consentito",JOptionPane.INFORMATION_MESSAGE);
						panel.setVisible(false);
						panelSystem = new JPanel();
						panelSystem.setBorder(new EmptyBorder(200,5,0,0));
						Font font = new Font("file", Font.ITALIC, 18);
						menuBar = new JMenuBar();
						menuBar.setVisible(true);
						menuBar.setBackground(gold);
						menuBar.setLayout(new GridLayout(1,3));
						setJMenuBar(menuBar);
						JMenu fileMenu = new JMenu("Visualizza");
						fileMenu.setForeground(Color.BLUE);
						fileMenu.setFont(font);
						JMenu creditMenu = new JMenu("User " + u.getNomeUt());
						creditMenu.setForeground(Color.BLUE);
						creditMenu.setFont(font);
						JMenu logoutMenu = new JMenu("File");
						logoutMenu.setForeground(Color.BLUE);
						logoutMenu.setFont(font);
						creditMenu.add(itemUser("Inserire credito"));
						creditMenu.add(itemUser("Storico Acquisti"));
						fileMenu.add(itemUser("Offerte"));
						fileMenu.add(itemUser("Scelta Ordinamento"));
						logoutMenu.add(itemUser("Aggiorna"));						
						logoutMenu.add(itemUser("Logout"));

						menuBar.add(logoutMenu);
						menuBar.add(fileMenu);
						menuBar.add(creditMenu);

						listaOffUser();
						controlPanelOfferte();
						insertCredito();
						visualizzaCredito(u);
						sceltaOrdinamento();
						storicoCliente();

						add(panelSystem);

					}
					else {
						JLabel msg = new JLabel("Username/Password non corretti");
						msg.setFont(textFont);
						msg.setForeground(Color.BLUE);
						JOptionPane.showMessageDialog(null,msg, "Accesso Negato",JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}	
		}

		//Accedi
		Font font = new Font ("button", Font.ITALIC, 14);
		LogUserListener logList = new LogUserListener();
		accedi = new JButton("Accedi");
		accedi.setFont(font);
		accedi.setForeground(Color.BLUE);
		accedi.setBackground(Color.LIGHT_GRAY);
		accedi.addActionListener(logList);
		panelButton.add(accedi);

		//Registrazione
		RegListener listener = new RegListener();
		registrazione = new JButton("Registrazione");
		registrazione.setFont(font);
		registrazione.setForeground(Color.BLUE);
		registrazione.setBackground(Color.LIGHT_GRAY);
		registrazione.addActionListener(listener);
		panelButton.add(registrazione);

		//Border - Layout
		panelUser.setLayout(new BorderLayout());
		panelUser.setBorder(new TitledBorder(new EtchedBorder(),"Area Utente"));
		panelArea.setLayout(new GridLayout(4,2));
		panelArea.setBorder(new EmptyBorder(120,10,10,10));

		//Background - Foreground - Font
		panelButton.setBackground(gold);
		panelId.setBackground(gold);
		panelPass.setBackground(gold);
		panelArea.setBackground(gold);
		username.setForeground(Color.BLUE);
		password.setForeground(Color.BLUE);
		username.setFont(huge);
		password.setFont(huge);

		//Add panel
		panelId.add(username);
		panelId.add(text);
		panelPass.add(password);
		panelPass.add(pass);
		panelArea.add(panelId);
		panelArea.add(panelPass);
		panelUser.add(panelArea,BorderLayout.NORTH);
		panelUser.add(panelButton,BorderLayout.CENTER);
		panelUser.setBackground(gold);
		panel.add(panelUser,BorderLayout.CENTER);
	}

	public JMenuItem itemUser (final String messaggio) {
		JMenuItem item = new JMenuItem(messaggio);
		huge = new Font("file", Font.ITALIC, 18);

		item.setFont(huge);
		item.setForeground(Color.BLUE);
		/////////CONTROL PANEL //////////////////////////////////

		class addItem implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				if (messaggio.equals("Offerte")) {
					panelSystem.setVisible(true);
					listaOffUser.setVisible(true);
					acquistaPanel.setVisible(true);
					insert.setVisible(false);
					rimuoviOff.setVisible(false);
					ordinamento.setVisible(false);
					storico.setVisible(false);
				}
				if(messaggio.equals("Inserire credito")){
					panelSystem.setVisible(true);
					insert.setVisible(true);
					listaOffUser.setVisible(false);
					acquistaPanel.setVisible(false);
					rimuoviOff.setVisible(false);
					ordinamento.setVisible(false);
					storico.setVisible(false);
				}

				if(messaggio.equals("Scelta Ordinamento")){
					panelSystem.setVisible(true);
					rimuoviOff.setVisible(false);
					listaOffUser.setVisible(false);
					acquistaPanel.setVisible(false);
					ordinamento.setVisible(true);
					insert.setVisible(false);
					storico.setVisible(false);

				}
				if (messaggio.equals("Aggiorna")) {
					panelSystem.setVisible(true);
					rimuoviOff.setVisible(false);
					ordinamento.setVisible(false);
					insert.setVisible(false);
					listaOffUser.setVisible(false);
					acquistaPanel.setVisible(false);
					storico.setVisible(false);
					listaOffUser();
					controlPanelOfferte();
					storicoCliente();
				}
				if (messaggio.equals("Storico Acquisti")) {
					panelSystem.setVisible(true);
					rimuoviOff.setVisible(false);
					ordinamento.setVisible(false);
					insert.setVisible(false);
					listaOffUser.setVisible(false);
					acquistaPanel.setVisible(false);
					storicoCliente();
					storico.setVisible(true);
				}
				if (messaggio.equals("Logout")) {
					panelSystem.setVisible(false);
					panel.setVisible(true);
					menuBar.setVisible(false);
				}
			}
		} 
		ActionListener listener = new addItem();
		item.addActionListener(listener);
		return item;
	}

	public void listaOffUser() {

		listaOffUser = new JPanel();
		listaOffUser.setVisible(false);
		list = new Font("list", Font.ITALIC, 16);
		JPanel panelList = new JPanel();
		panelList.setLayout(new BorderLayout());
		listaOffUser.setLayout(new BorderLayout());
		ObjectInputStream input = null;
		try {		
			input = new ObjectInputStream(new FileInputStream("catalogo.dat"));

		} catch (FileNotFoundException e1) {	
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Offerte off = null;

		JTextArea text = new JTextArea(20,43);

		for(;;){
			try {
				off=(Offerte)input.readObject();
				text.append("\n" +off);
				text.append("\n");
			}
			catch(Exception e){
				break;
			}
		}

		text.setFont(list);
		text.setForeground(Color.BLUE);
		text.setBackground(Color.LIGHT_GRAY);
		panelList.add(text,BorderLayout.NORTH);
		scrol = new JScrollPane(text);
		panelList.add(scrol,BorderLayout.CENTER);
		listaOffUser.add(panelList,BorderLayout.NORTH);
		controlPanelOfferte();
	}

	public void insertCredito() {

		Font font = new Font ("button", Font.ITALIC + Font.BOLD, 14);

		insert = new JPanel();
		insert.setVisible(false);
		JLabel credito = new JLabel("Credito:");
		credito.setFont(font);
		credito.setForeground(Color.BLUE);
		final JTextField creditoText = new JTextField(10);
		JButton ok = new JButton("Ok");
		ok.setFont(font);
		ok.setForeground(Color.BLUE);

		class inserisci implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				double soldi = Double.parseDouble(creditoText.getText());
				u.credito(soldi);
				insert.setVisible(false);
				visualizzaCredito(u);
			}
		}
		ActionListener listener = new inserisci();
		ok.addActionListener(listener);

		insert.add(credito);
		insert.add(creditoText);
		insert.add(ok);
		panelSystem.add(insert, BorderLayout.CENTER);	
	}

	public void visualizzaCredito(User u) {
		Font font=new Font("font",Font.PLAIN+Font.BOLD,15);
		rimuoviOff = new JPanel();
		rimuoviOff.setVisible(true);
		rimuoviOff.setBorder(new EmptyBorder(400,400,5,5));
		double credit = u.getCredito();
		String s = String.valueOf(credit).toString();
		JLabel credito = new JLabel(s);
		credito.setForeground(Color.BLUE);
		credito.setFont(font);
		JLabel corrente = new JLabel("Saldo corrente");
		corrente.setForeground(Color.BLUE);
		corrente.setFont(font);
		rimuoviOff.add(corrente);
		rimuoviOff.add(credito);
		panelSystem.add(rimuoviOff,BorderLayout.SOUTH);
	}

	public void sceltaOrdinamento() {
		JLabel label = new JLabel ("Scelta Ordinamento : ");
		JButton id = new JButton("Id");
		JButton crono = new JButton("Cronologico");

		Font font = new Font ("button", Font.ITALIC + Font.BOLD, 14);

		label.setFont(font);
		label.setForeground(Color.BLUE);
		id.setFont(font);
		id.setForeground(Color.BLUE);
		crono.setFont(font);
		crono.setForeground(Color.BLUE);
		ordinamento = new JPanel();
		ordinamento.setVisible(false);

		class idListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					a = new Admin();
					a.sort();
					listaOffUser();
					ordinamento.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		ActionListener listenerId = new idListener();
		id.addActionListener(listenerId);

		class cronoListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					a = new Admin();
					a.OrdinamentoCrono();
					a.salva();
					listaOffUser();
					ordinamento.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}	
		}
		ActionListener listenerCrono = new cronoListener();
		crono.addActionListener(listenerCrono);

		ordinamento.add(label);
		ordinamento.add(id);
		ordinamento.add(crono);
		panelSystem.add(ordinamento);
	}

	public void controlPanelOfferte() {

		JButton fornitore = new JButton("Fornitore");
		JButton scadenza = new JButton("Scadenza");
		JButton acquista = new JButton("Acquista");
		JPanel button = new JPanel();
		acquistaPanel = new JPanel();
		acquistaPanel.setVisible(false);
		JPanel aPanel = new JPanel();
		JLabel label = new JLabel ("Id: ");
		JLabel label2 = new JLabel("Quantita': ");
		final JTextField textq = new JTextField(2);
		final JTextField textField = new JTextField(2);

		final Font font = new Font ("button", Font.ITALIC + Font.BOLD, 14);
		fornitore.setFont(font);
		scadenza.setFont(font);
		acquista.setFont(font);
		label.setFont(font);
		label2.setFont(font);
		fornitore.setBackground(gold);
		scadenza.setBackground(gold);
		acquista.setBackground(gold);
		label.setBackground(gold);
		label2.setBackground(gold);
		fornitore.setForeground(Color.BLUE);
		scadenza.setForeground(Color.BLUE);
		acquista.setForeground(Color.BLUE);
		label.setForeground(Color.BLUE);
		label2.setForeground(Color.BLUE);

		class scadenzaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				final JFrame scadenzaframe = new JFrame(); 
				final JPanel scadenzaPanel = new JPanel();
				final JPanel general = new JPanel();
				general.setLayout(new GridLayout(5,10));
				final JPanel buttonPanel = new JPanel();
				scadenzaPanel.setBorder(new EmptyBorder(5,5,5,5));
				final JPanel dataPanel = new JPanel();
				final JPanel dataFPanel = new JPanel();
				final JPanel scadenzaFPanel = new JPanel();
				JLabel dataInit = new JLabel("Data iniziale");
				dataInit.setFont(font);
				dataInit.setForeground(Color.BLUE);
				JLabel dataEnd = new JLabel("Data finale");
				dataEnd.setFont(font);
				dataEnd.setForeground(Color.BLUE);
				final JComboBox<Integer> combo1 = new JComboBox<Integer>();
				combo1.setFont(font);
				combo1.setForeground(Color.BLUE);
				final JComboBox<Integer> combo2 = new JComboBox<Integer>();
				combo2.setFont(font);
				combo2.setForeground(Color.BLUE);
				final JComboBox<Integer> combo3 = new JComboBox<Integer>();
				combo3.setFont(font);
				combo3.setForeground(Color.BLUE);
				final JComboBox<Integer> combo4 = new JComboBox<Integer>();
				combo4.setFont(font);
				combo4.setForeground(Color.BLUE);
				final JComboBox<Integer> combo5 = new JComboBox<Integer>();
				combo5.setFont(font);
				combo5.setForeground(Color.BLUE);
				final JComboBox<Integer> combo6 = new JComboBox<Integer>();
				combo6.setFont(font);
				combo6.setForeground(Color.BLUE);
				JButton button = new JButton("OK");
				button.setFont(font);
				button.setForeground(Color.BLUE);
				button.setBackground(gold);
				for (int i = 1; i<32; i++){
					combo1.addItem(i);
					combo4.addItem(i);
				}
				for (int i = 1; i<13; i++){
					combo2.addItem(i);
					combo5.addItem(i);
				}
				for (int i = 2014; i < 2020; i++){
					combo3.addItem(i);
					combo6.addItem(i);
				}
				scadenzaPanel.add(dataInit);
				dataPanel.add(combo1);
				dataPanel.add(combo2);
				dataPanel.add(combo3);
				scadenzaFPanel.add(dataEnd);
				dataFPanel.add(combo4);
				dataFPanel.add(combo5);
				dataFPanel.add(combo6);
				buttonPanel.add(button);
				general.add(scadenzaPanel);
				general.add(dataPanel);
				general.add(scadenzaFPanel);
				general.add(dataFPanel);
				general.add(buttonPanel);
				scadenzaframe.add(general);
				scadenzaframe.setSize(350,320);
				scadenzaframe.setVisible(true);
				scadenzaframe.setResizable(false);

				class buttonListener implements ActionListener {
					public void actionPerformed(ActionEvent e) {
						general.setVisible(false);
						JPanel areaPanel = new JPanel();
						JTextArea text = new JTextArea(18,32);
						text.setEditable(false);
						ObjectInputStream input = null;
						try {		
							input = new ObjectInputStream(new FileInputStream("catalogo.dat"));

						} catch (FileNotFoundException e1) {	
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						Offerte off=null;

						int giorno = (int) combo1.getSelectedItem();
						int mese = (int) combo2.getSelectedItem();
						int anno = (int) combo3.getSelectedItem();
						int giornoF = (int) combo4.getSelectedItem();
						int meseF = (int) combo5.getSelectedItem();
						int annoF = (int) combo6.getSelectedItem();

						GregorianCalendar inizio = new GregorianCalendar(anno,mese-1,giorno);
						GregorianCalendar fine = new GregorianCalendar(annoF,meseF-1,giornoF);

						for(;;) {
							try{
								off = (Offerte)input.readObject();

								if(off instanceof Cene){
									Cene c = (Cene) off;
									if (c.getScadenza().after(inizio)) {
										if (c.getScadenza().before(fine)) {
											text.append("\n" + off);
											text.append("\n");
										}
									}
								}

								if (off instanceof Vacanze) {
									Vacanze v = (Vacanze) off;
									if (v.getScadenza().after(inizio)) {
										if (v.getScadenza().before(fine)) {
											text.append("\n" + off);
											text.append("\n");			
										}
									}
								}
								else ;
							}
							catch(Exception c){
								break;
							}
						}

						areaPanel.add(text);
						JScrollPane scroll = new JScrollPane(text);
						areaPanel.add(scroll);
						scadenzaframe.add(areaPanel);
					}
				}
				ActionListener listener3 = new buttonListener();
				button.addActionListener(listener3);
			}
		}
		ActionListener listener2 = new scadenzaListener();
		scadenza.addActionListener(listener2);		

		class fornitoreListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				JFrame fornitoreFrame = new JFrame();
				JPanel fornitorePanel = new JPanel();
				JLabel msg = new JLabel("Inserire giudizio (1-5)");
				msg.setFont(font);
				msg.setForeground(Color.BLUE);
				String giudizio = JOptionPane.showInputDialog(msg);
				int numero = Integer.parseInt(giudizio);
				JTextArea fornitoreText = new JTextArea(22,24);
				fornitoreText.setFont(font);
				fornitoreText.setForeground(Color.BLUE);
				fornitoreText.setBackground(Color.LIGHT_GRAY);
				fornitoreText.setEditable(false);

				ObjectInputStream input = null;
				try {		
					input = new ObjectInputStream(new FileInputStream("catalogo.dat"));
				} catch (FileNotFoundException e1) {	
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				Offerte off=null;
				boolean flag = false;

				for(;;){
					try{
						off = (Offerte)input.readObject();
						if(off instanceof BeniDiConsumo){
							BeniDiConsumo b = (BeniDiConsumo) off;
							if (b.getGiudizio() >= numero) {
								fornitoreText.append("\n" + off);
								fornitoreText.append("\n");
								flag = true;
							}
						}
						if (off instanceof PrestazioniOpera) {
							PrestazioniOpera p = (PrestazioniOpera) off;
							if (p.getGiudizio() >= numero) {
								fornitoreText.append("\n" + off);
								fornitoreText.append("\n");	
								flag = true;
							}
						}
						else ;

						fornitorePanel.add(fornitoreText);
						JScrollPane scroll = new JScrollPane(fornitoreText);
						fornitorePanel.add(scroll);
						fornitoreFrame.add(fornitorePanel);

						fornitoreFrame.setSize(350,420);
						fornitoreFrame.setVisible(true);
						fornitoreFrame.setLocation(1, 320);
						fornitoreFrame.setResizable(false);
						fornitoreFrame.setUndecorated(true);
					}
					catch(Exception c){
						break;
					}
				}
				if (flag == false) {
					JOptionPane.showMessageDialog(null, "Nessun Fornitore presente con giudizio "+ numero);
					fornitoreFrame.setVisible(false);	
				}
			}	
		}
		ActionListener listener = new fornitoreListener();
		fornitore.addActionListener(listener);


		class acquistaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					a = new Admin();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
				String id = textField.getText();
				String q = textq.getText();
				int numeroId = Integer.parseInt(id);
				int quantita = Integer.parseInt(q);
				String result = u.acquista(numeroId, a.getCat(), quantita);
				JOptionPane.showMessageDialog(null, result, "Acquisto", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (Exception e3) {
					JOptionPane.showMessageDialog(null, e3.getMessage());
				}
				try {
					a.salva();
				} catch (EOFException e1) {
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		}
		ActionListener listener3 = new acquistaListener();
		acquista.addActionListener(listener3);

		button.add(scadenza);
		button.add(fornitore);
		aPanel.add(label);
		aPanel.add(textField);
		aPanel.add(label2);
		aPanel.add(textq);
		aPanel.add(acquista);	
		acquistaPanel.add(button,BorderLayout.WEST);
		acquistaPanel.add(aPanel,BorderLayout.EAST);
		panelSystem.add(listaOffUser);
		panelSystem.add(acquistaPanel);
	}

	public void storicoCliente() {
		storico = new JPanel();
		storico.setVisible(false);
		storico.setLayout(new BorderLayout());

		JTextArea areaText = new JTextArea(25,55);
		areaText.setEditable(false);
		areaText.setBackground(Color.LIGHT_GRAY);
		areaText.setForeground(Color.BLUE);
		for (int i = 0; i < u.size() ; i++) {
			areaText.append(u.getItem(i).toString());
			areaText.append("\n");
			storico.add(areaText);
			JScrollPane scrol = new JScrollPane(areaText);
			storico.add(scrol);
			panelSystem.add(storico,BorderLayout.NORTH);
		}
	}

	class RegListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			registraUser();
			areaUtente();
		}
	}

	public void titleReg() {
		JLabel titoloReg = new JLabel("Registrazione");
		JPanel panelTitoloReg = new JPanel();
		huge = new Font(("Calibra"), Font.ITALIC + Font.BOLD, 72);
		titoloReg.setFont(huge);
		titoloReg.setForeground(Color.BLUE);
		panelTitoloReg.add(titoloReg);
		panelTitoloReg.setBackground(gold);
		panelReg.add(panelTitoloReg,BorderLayout.NORTH);
	}

	class MainListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			panel.setVisible(true);
			setVisible(true);
		}
	}

	public void registraUser() {
		frame = new JFrame();

		panelReg = new JPanel(); //Principale
		JPanel panelArea = new JPanel(); //Esterno
		JPanel panelName = new JPanel(); //User
		JPanel panelPassword = new JPanel();//Password
		JPanel panelButton = new JPanel(); //Button

		Font font = new Font ("button", Font.ITALIC, 14);

		//Listener Ok
		ActionListener logFirstList = new RegFirstListener();
		accedi = new JButton("Registra");
		accedi.setBackground(Color.LIGHT_GRAY);
		accedi.setForeground(Color.BLUE);
		accedi.setFont(font);
		accedi.addActionListener(logFirstList);

		ActionListener main = new MainListener();
		indietro = new JButton("Indietro");
		indietro.addActionListener(main);
		indietro.setBackground(Color.LIGHT_GRAY);
		indietro.setForeground(Color.BLUE);
		indietro.setFont(font);

		panelButton.add(accedi);
		panelButton.add(indietro);

		//Label - Field
		JLabel nomeLabel = new JLabel("Username");
		nomeText = new JTextField(15);
		JLabel cognomeLabel = new JLabel("Password");
		passwordText = new JPasswordField (15);

		//Layout
		panelReg.setLayout(new BorderLayout());
		panelArea.setLayout(new GridLayout(4,2));
		panelArea.setBorder(new EmptyBorder(50,-300,0,0));

		titleReg();

		huge = new Font(("TimesRoman"), Font.ITALIC+Font.BOLD, 25);

		nomeLabel.setFont(huge);
		cognomeLabel.setFont(huge);
		nomeLabel.setForeground(Color.BLUE);
		cognomeLabel.setForeground(Color.BLUE);
		panelButton.setBackground(Color.LIGHT_GRAY);
		panelName.setBackground(Color.LIGHT_GRAY);
		panelPassword.setBackground(Color.LIGHT_GRAY);
		panelArea.setBackground(Color.LIGHT_GRAY);

		panelName.add(nomeLabel);
		panelName.add(nomeText);
		panelPassword.add(cognomeLabel);
		panelPassword.add(passwordText);

		panelArea.add(panelName);
		panelArea.add(panelPassword);
		panelArea.add(panelButton);
		panelReg.add(panelArea,BorderLayout.CENTER);


		frame.setUndecorated(true);
		frame.setSize(665,700);
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize( );
		frame.setLocation ((screenSize.width/2)-(frame.getWidth( )/2) , (screenSize.height/2)-(frame.getHeight( )/2));
		frame.add(panelReg);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	class RegFirstListener implements ActionListener {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			try {
				u = new User("userLogin.txt");
				int r = u.login("no", nomeText.getText(), passwordText.getText());
				if (r == 1) {
					frame.setVisible(false);
					panel.setVisible(true);
					setVisible(true);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
	}
	///////////////////////////////////////////////////////////////////////////////////

	//Variabili 
	private User u;
	private JMenuBar menuBar;
	private JButton accedi, registrazione, indietro,accediAdmin;
	private Color gold;
	private Font huge,list;
	private JRadioButton admin;
	private JPanel panel,panelSystem,panelReg,logAdmin,storico,acquistaPanel;
	private JPanel listaOff, insert, sconti,tipoOfferta,scelta,rimuoviOff,ordinamento,listaOffUser;
	private JFrame frame;
	private JScrollPane scrol;
	private Admin a;
	private JComboBox<String> offerte;
	private JComboBox<Integer> giorno,mese,anno,giornoS,meseS,annoS,voto;
	private JPasswordField passwordText;
	private JTextField nomeText;
}