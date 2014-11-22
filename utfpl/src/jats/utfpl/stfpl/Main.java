package jats.utfpl.stfpl;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;



public class Main {
    
    public enum ModelType {
        csps
    }
    
    private ModelType m_type;
    private String m_input;
    private String m_output;
    
    public void options(PrintStream stm) {
        stm.println("usage: java -jar mcatscc.jar <command> ... <command>");

        stm.println("where a <command> is of one of the following forms:");
        stm.println();
        stm.println("  -c, --csps");
        stm.println("               Generate model in CSP#.");
        stm.println("  -i, --input filenames");
        stm.println("               Specify the name of the input file.");
        stm.println("  -o, --output filenames");
        stm.println("               Specify the name of the output file.");
        stm.println();

    }

    public Main() {
        m_type = null;
        m_input = null;
        m_output = null;
    }

    public int parseArgv(String argv[]) {
        for (int i = 0; i < argv.length; ++i) {
            String arg = argv[i];

            if (arg.equals("-c") || arg.equals("--csps")) {
                m_type = ModelType.csps;
            } else if (arg.equals("-i") || arg.equals("--input")) {
                if ((i + 1) >= argv.length) {
                    System.err
                            .println("Error: Please specify the name of the input file.\n");
                    options(System.err);
                    return -1;
                } else {
                    m_input = argv[i + 1];
                    i = i + 1;
                }
            } else if (arg.equals("-o") || arg.equals("--output")) {
                if ((i + 1) >= argv.length) {
                    System.err
                            .println("Error: Please specify the name of the output file.\n");
                    options(System.err);
                    return -1;
                } else {
                    m_output = argv[i + 1];
                    i = i + 1;
                }
            } else {
                System.err.println("unknow options");
                options(System.err);
                return -1;
            }
        }

        if (null == m_type || null == m_input) {
            System.err.println("Error: Please provide more information.\n");
            options(System.err);
            return -1;
        }

        return 0;
    }

    public int generateModel() throws IOException {
        try {
            
            File path = new File(m_input);
            if (!path.exists()) {
                System.err.println("Input file doesn't exist.");
                return -1;
            }
            ModelGenerater mcGen = new ModelGenerater(path.getAbsolutePath(), m_output);
            mcGen.generate(8);
                        
            /* ******** ******** */
            
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        } finally {
        }
        
        return 0;

    }

    public static void main(String argv[]) throws IOException {
        Main tg = new Main();
        if (tg.parseArgv(argv) != 0) {
            return;
        }

        if (tg.generateModel() != 0) {
            return;
        }

    }
}
