import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class App {
    static String[] algoritmos = {
        "Bubble Sort",
        "Merge Sort",
        "Selection Sort",
        "Insertion Sort"
    };

    // ===================== LÓGICA (inalterada) =====================

    public static int[] bubbleSort(int[] numeros) {
        return numeros;
    }

    public static int[] mergeSort(int[] numeros) {
        return numeros;
    }

    public static int[] selectionSort(int[] numeros) {
        for (int i = 0; i < numeros.length - 1; i++) {
            int posicaoMenor = i;

            // Procura o menor número entre i e o final do array
            for (int j = i + 1; j < numeros.length; j++) {
                if (numeros[j] < numeros[posicaoMenor]) {
                    posicaoMenor = j;
                }
            }

            // Troca o menor número com o número da posição i
            int temporario = numeros[i];
            numeros[i] = numeros[posicaoMenor];
            numeros[posicaoMenor] = temporario;
        }

        return numeros;
    }

    public static int[] insertionSort(int[] numeros) {
        return numeros;
    }

    // ===================== ESTILO (novo) =====================

    static final Color COR_FUNDO = new Color(0x14101F);
    static final Color COR_CARD = new Color(0x1F1933);
    static final Color COR_CABECALHO = new Color(0x2A1B4D);
    static final Color COR_DESTAQUE = new Color(0x8B5CF6);
    static final Color COR_DESTAQUE_HOVER = new Color(0xA78BFA);
    static final Color COR_SELECAO = new Color(0x4C2F8F);
    static final Color COR_TEXTO = new Color(0xEDE9FE);
    static final Color COR_TEXTO_SUAVE = new Color(0x9F93C4);
    static final Color COR_BORDA = new Color(0x3B2D66);

    static final Font FONTE_ROTULO = new Font("SansSerif", Font.BOLD, 13);
    static final Font FONTE_CAMPO = new Font("SansSerif", Font.PLAIN, 14);

    static JLabel criarRotulo(String texto) {
        JLabel rotulo = new JLabel(texto);
        rotulo.setFont(FONTE_ROTULO);
        rotulo.setForeground(COR_TEXTO);
        rotulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        rotulo.setBorder(new EmptyBorder(0, 2, 6, 0));
        return rotulo;
    }

    static void estilizarComponente(JComponent componente, int altura) {
        componente.setAlignmentX(Component.LEFT_ALIGNMENT);
        componente.setMaximumSize(new Dimension(Integer.MAX_VALUE, altura));
        componente.setPreferredSize(new Dimension(100, altura));
    }

    static Border bordaCampo() {
        return BorderFactory.createCompoundBorder(
            new LineBorder(COR_BORDA, 1, true),
            new EmptyBorder(6, 10, 6, 10)
        );
    }

    // ===================== INTERFACE =====================

    public static void janelinha() {
        JFrame janela = new JFrame("Ordenação");
        janela.setSize(460, 580);
        janela.setMinimumSize(new Dimension(400, 520));
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new BorderLayout());

        // ---------- Cabeçalho ----------
        JPanel cabecalho = new JPanel();
        cabecalho.setLayout(new BoxLayout(cabecalho, BoxLayout.Y_AXIS));
        cabecalho.setBackground(COR_CABECALHO);
        cabecalho.setBorder(new EmptyBorder(20, 24, 20, 24));

        JLabel titulo = new JLabel("Ordenação de números");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel("Compare os algoritmos e veja o tempo gasto");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subtitulo.setForeground(new Color(0xD4D3F5));

        cabecalho.add(titulo);
        cabecalho.add(Box.createVerticalStrut(4));
        cabecalho.add(subtitulo);
        janela.add(cabecalho, BorderLayout.NORTH);

        // ---------- Corpo ----------
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(COR_FUNDO);
        painel.setBorder(new EmptyBorder(22, 24, 22, 24));
        janela.add(painel, BorderLayout.CENTER);

        JLabel mensagem1 = criarRotulo(
            "Digite inteiros com vírgulas ou algarismos:"
        );
        JTextField campoNumeros = new JTextField(20);
        campoNumeros.setFont(FONTE_CAMPO);
        campoNumeros.setForeground(COR_TEXTO);
        campoNumeros.setBackground(COR_CARD);
        campoNumeros.setCaretColor(COR_DESTAQUE_HOVER);
        campoNumeros.setSelectionColor(COR_SELECAO);
        campoNumeros.setBorder(bordaCampo());
        estilizarComponente(campoNumeros, 40);

        JLabel dica = new JLabel("Ex.: 98, 7, 321   ou   987654321");
        dica.setFont(new Font("SansSerif", Font.ITALIC, 11));
        dica.setForeground(COR_TEXTO_SUAVE);
        dica.setAlignmentX(Component.LEFT_ALIGNMENT);
        dica.setBorder(new EmptyBorder(4, 2, 0, 0));

        JLabel mensagem2 = criarRotulo("Escolha o método:");
        JComboBox<String> categoria = new JComboBox<>(algoritmos);
        categoria.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                BasicArrowButton seta = new BasicArrowButton(
                    BasicArrowButton.SOUTH,
                    COR_CARD, COR_CARD, COR_DESTAQUE_HOVER, COR_CARD
                );
                seta.setBorder(new EmptyBorder(0, 0, 0, 0));
                return seta;
            }
        });
        categoria.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                JList<?> lista, Object valor, int indice,
                boolean selecionado, boolean foco
            ) {
                super.getListCellRendererComponent(
                    lista, valor, indice, selecionado, foco
                );
                setBackground(selecionado ? COR_SELECAO : COR_CARD);
                setForeground(COR_TEXTO);
                setBorder(new EmptyBorder(6, 10, 6, 10));
                return this;
            }
        });
        categoria.setFont(FONTE_CAMPO);
        categoria.setBackground(COR_CARD);
        categoria.setForeground(COR_TEXTO);
        categoria.setBorder(new LineBorder(COR_BORDA, 1, true));
        estilizarComponente(categoria, 38);

        JLabel mensagem3 = criarRotulo("Resultado:");
        JTextArea campoResultado = new JTextArea(5, 20);
        campoResultado.setEditable(false);
        campoResultado.setFont(new Font("Monospaced", Font.PLAIN, 14));
        campoResultado.setForeground(COR_TEXTO);
        campoResultado.setBackground(COR_CARD);
        campoResultado.setLineWrap(true);
        campoResultado.setWrapStyleWord(true);
        campoResultado.setBorder(new EmptyBorder(10, 12, 10, 12));

        JScrollPane rolagem = new JScrollPane(campoResultado);
        rolagem.setBorder(new LineBorder(COR_BORDA, 1, true));
        rolagem.getViewport().setBackground(COR_CARD);
        rolagem.setAlignmentX(Component.LEFT_ALIGNMENT);
        rolagem.setPreferredSize(new Dimension(100, 140));

        // Botão arredondado com efeito hover
        JButton botao = new JButton("Preparar números") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
                );
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        botao.setFont(new Font("SansSerif", Font.BOLD, 14));
        botao.setForeground(Color.WHITE);
        botao.setBackground(COR_DESTAQUE);
        botao.setFocusPainted(false);
        botao.setContentAreaFilled(false);
        botao.setOpaque(false);
        botao.setBorder(new EmptyBorder(10, 20, 10, 20));
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        estilizarComponente(botao, 44);
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setBackground(COR_DESTAQUE_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botao.setBackground(COR_DESTAQUE);
            }
        });

        painel.add(mensagem1);
        painel.add(campoNumeros);
        painel.add(dica);
        painel.add(Box.createVerticalStrut(18));
        painel.add(mensagem2);
        painel.add(categoria);
        painel.add(Box.createVerticalStrut(18));
        painel.add(botao);
        painel.add(Box.createVerticalStrut(18));
        painel.add(mensagem3);
        painel.add(rolagem);

        // Enter no campo de texto aciona o botão
        janela.getRootPane().setDefaultButton(botao);

        // ===================== LÓGICA DO BOTÃO (inalterada) =====================
        botao.addActionListener(e -> {
            try {
                String texto = campoNumeros.getText().trim();
                int[] numerosDigitados;

                if (texto.isEmpty()) {
                    throw new NumberFormatException();
                }

                if (texto.contains(",")) {
                    // Com vírgulas: cada parte é um número inteiro.
                    
                    String[] partes = texto.split(",");
                    numerosDigitados = new int[partes.length];

                    for (int i = 0; i < partes.length; i++) {
                        numerosDigitados[i] =
                            Integer.parseInt(partes[i].trim());
                    }
                } else {
                    // Sem vírgulas: cada algarismo é um número.
                  
                    numerosDigitados = new int[texto.length()];

                    for (int i = 0; i < texto.length(); i++) {
                        char caractere = texto.charAt(i);

                        if (!Character.isDigit(caractere)) {
                            throw new NumberFormatException();
                        }

                        numerosDigitados[i] =
                            Character.getNumericValue(caractere);
                    }
                }

                String metodoEscolhido =
                    (String) categoria.getSelectedItem();

                int[] resultado;
                long inicio = System.nanoTime();

                switch (metodoEscolhido) {
                    case "Bubble Sort":
                        resultado = bubbleSort(numerosDigitados);
                        break;
                    case "Merge Sort":
                        resultado = mergeSort(numerosDigitados);
                        break;
                    case "Selection Sort":
                        resultado = selectionSort(numerosDigitados);
                        break;
                    default:
                        resultado = insertionSort(numerosDigitados);
                        break;
                }

                long fim = System.nanoTime();
                long tempoGasto = fim - inicio;

                campoResultado.setText(
                    Arrays.toString(resultado)
                    + "\nTempo gasto: " + tempoGasto + " nanossegundos"
                );

            } catch (NumberFormatException erro) {
                campoResultado.setText(
                    "Entrada inválida. Use inteiros separados por vírgula "
                    + "ou digite apenas algarismos."
                );
            }
        });

        janela.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::janelinha);
    }
}