package daw;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Panel extends JPanel implements ActionListener{
    
	// Atributos de la clase (privados)
	private PanelBotones botonera;
	private JTextArea areaTexto;
	private int tipoOperacion;
    private double primerOperando;
    private boolean esperandoSegundoOperando;

    
	// Constructor
	public Panel(){
    		initComponents();
    		tipoOperacion = -1; // No hay operaciones en la calculadora
            primerOperando = 0;
            esperandoSegundoOperando = false;
    
	}
    
	// Se inicializan los componentes gráficos y se colocan en el panel
	private void initComponents(){
    		// Creamos el panel de botones
    		botonera = new PanelBotones();
    		// Creamos el área de texto
    		areaTexto = new JTextArea(10,50);
    		areaTexto.setEditable(false);
    		areaTexto.setBackground(Color.white);
   
    		//Establecemos layout del panel principal
    		this.setLayout(new BorderLayout());
    		// Colocamos la botonera y el área texto
    		this.add(areaTexto, BorderLayout.NORTH);
    		this.add(botonera, BorderLayout.SOUTH);

            for (JButton boton: this.botonera.getGrupoBotones()){
                boton.addActionListener(this);
            }
    
	 
	}
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object o = ae.getSource();
        if (o instanceof JButton){
            String textoBoton = ((JButton) o).getText();

            switch (textoBoton) {
                case "+":
                    guardarOperando(1);
                    break;
                case "-":
                    guardarOperando(2);
                    break;
                case "*":
                    guardarOperando(3);
                    break;
                case "/":
                    guardarOperando(4);
                    break;
                case "=":
                    realizarOperacion();
                    break;
                case "C":
                    areaTexto.setText("");
                    primerOperando = 0;
                    tipoOperacion = -1;
                    esperandoSegundoOperando = false;
                    break;
                default: // números
                    if (esperandoSegundoOperando) {
                        areaTexto.setText(textoBoton);
                        esperandoSegundoOperando = false;
                    } else {
                        areaTexto.append(textoBoton);
                    }
                    break;
            }
        }
    }    private void guardarOperando(int tipo) {
        try {
            primerOperando = Double.parseDouble(areaTexto.getText());
            tipoOperacion = tipo;
            esperandoSegundoOperando = true;
        } catch (NumberFormatException e) {
            areaTexto.setText("Error");
        }
    }

    private void realizarOperacion() {
        try {
            double segundoOperando = Double.parseDouble(areaTexto.getText());
            double resultado = 0;
            switch (tipoOperacion) {
                case 1: resultado = primerOperando + segundoOperando; break;
                case 2: resultado = primerOperando - segundoOperando; break;
                case 3: resultado = primerOperando * segundoOperando; break;
                case 4:
                    if (segundoOperando == 0) {
                        areaTexto.setText("División por 0");
                        return;
                    } else {
                        resultado = primerOperando / segundoOperando;
                    }
                    break;
                default: return;
            }
            // Segun el tipo de Operacion se muestra el resultado de una manera diferente 
            if (tipoOperacion >= 1 && tipoOperacion <= 4) {
                switch (tipoOperacion) {
                    case 1:
                    areaTexto.setText( primerOperando + " "+  "+" + segundoOperando + "=" + Double.toString(resultado));
                    esperandoSegundoOperando = false;
                        break;
                        case 2:
                        areaTexto.setText( primerOperando + " "+  "-" + segundoOperando + "=" + Double.toString(resultado));
                        esperandoSegundoOperando = false;
                        break;
                        case 3:
                        areaTexto.setText( primerOperando + " "+  "*" + segundoOperando + "=" + Double.toString(resultado));
                        esperandoSegundoOperando = false;
                        break;

                        case 4:
                        areaTexto.setText( primerOperando + " "+  "/" + segundoOperando + "=" + Double.toString(resultado));
                        esperandoSegundoOperando = false;
                        break;
                
                    default:
                        break;
                }
            }
           
        } catch (NumberFormatException e) {
            areaTexto.setText("Error");
        }
    }
}




