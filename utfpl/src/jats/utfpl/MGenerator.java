package jats.utfpl;

import jats.utfpl.ccomp.CCompUtils;
import jats.utfpl.csps.CSPSTransformer;
import jats.utfpl.csps.ProgramCSPS;
import jats.utfpl.instruction.InstructionClosureConverter;
import jats.utfpl.instruction.InstructionProgramProcessor;
import jats.utfpl.instruction.InstructionTransformer;
import jats.utfpl.instruction.ProgramInstruction;
import jats.utfpl.instruction.TID;
import jats.utfpl.parser.NamingVisitor;
import jats.utfpl.parser.UtfplLexer;
import jats.utfpl.parser.UtfplParser;
import jats.utfpl.parser.Utfpl_tree;
import jats.utfpl.patcsps.PATCSPSPrinter;
import jats.utfpl.patcsps.PModel;
import jats.utfpl.patcsps.PatCspsTransformer;
import jats.utfpl.stfpl.StfplProgramParserJson;
import jats.utfpl.stfpl.dynexp.ProgramStfpl2;
import jats.utfpl.tree.ProgramTree;
import jats.utfpl.tree.TreeFromUtfpl;
import jats.utfpl.utils.FilenameUtils;
import jats.utfpl.utils.MapScope;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

public class MGenerator {
    
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

    public MGenerator() {
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
            ProgramTree prog = null;
            
            if (FilenameUtils.isATS(path)) {
                path = FilenameUtils.toJson(path);
                
                String cmd = "patsopt -o " + path.getPath() + " --jsonize-2 -d " + m_input + " 2>&1";
//                System.out.println("cmd is " + cmd);
                Process child = Runtime.getRuntime().exec(cmd);
                int returnCode = child.waitFor();
//                System.out.println("returnCode is " + returnCode);
                if (0 == returnCode) {
                    FileReader fReader = new FileReader(path);

                    StfplProgramParserJson utfplParser = new StfplProgramParserJson();
                    ProgramStfpl2 uProg = utfplParser.trans(fReader);

//                    UtfplPrinter uPrinter = new UtfplPrinter();
//                    String outputUTFPL = uPrinter.print(uProg);
//                    
//                    System.out.println("==utfpl's ast code (layer 01) is ==========================");
//                    
//                    System.out.println(outputUTFPL);
                    
//x                    UtfplProgramProcessor processor = new UtfplProgramProcessor();
//x                    uProg = processor.removeProof(uProg);
//                    outputUTFPL = uPrinter.print(uProg);
                    
//                    System.out.println("==utfpl's ast code (layer 02) is ==========================");
//                    
//                    System.out.println(outputUTFPL);
//                    
//                    FileWriter fwUTFPL = new FileWriter(FilenameUtils.changeExt(path, FilenameUtils.cUTFPL));
//                    BufferedWriter bwUTFPL = new BufferedWriter(fwUTFPL);
//                    bwUTFPL.write(outputUTFPL);
//                    bwUTFPL.close();
                    
                    TreeFromUtfpl treeV = new TreeFromUtfpl();
                    
                    prog = treeV.trans(uProg);
                } else {
//                    System.err.println("atsopt failed");
                    String line;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(child.getErrorStream()));
                    while ((line = reader.readLine()) != null) {
                        System.err.println(line);
                    }
                    return -1;                 
                }

            } else {
                ANTLRFileStream fileStream = new ANTLRFileStream(path.getPath());
                
                /* ******** ******** */
                // lexing
                UtfplLexer lexer = new UtfplLexer(fileStream);
                TokenStream tokenStream = new CommonTokenStream(lexer);
                // System.out.println(tokenStream.toString());
                
                /* ******** ******** */
                // parsing
                UtfplParser parser = new UtfplParser(tokenStream);  // create worker
                UtfplParser.rule_return parser_ret = parser.rule();  // worker works
                CommonTree tree = (CommonTree)parser_ret.getTree();
                CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
                
                /* ******** ******** */
                // tree parsing
                Utfpl_tree walker = new Utfpl_tree(nodes);  // create worker

                prog = walker.rule();  // worker works
            }
     
            
            /* ***************** ****************** */
            // naming construction
            MapScope<TID> libScope = new MapScope<TID>();
            CCompUtils.populateAllFunctions(libScope);
            NamingVisitor nameV = new NamingVisitor(libScope);
            prog.accept(nameV);
            
            /* ***************** ****************** */
            // print tree
//            TreePrinter tp = new TreePrinter();  // create worker
//            String output1 = tp.print(prog);  // worker works
//            
//            System.out.println("==program is ==========================");
//            System.out.println(output1);
            
            // ================== ====================== ====================
            
            /* ***************** ****************** */
            // generate program of instructions
            InstructionTransformer insV = new InstructionTransformer();  // create worker
            ProgramInstruction programIns = insV.trans(prog);  // worker works
            
            /* ***************** ****************** */
            // print instructions
//            InstructionPrinter insPrinter = new InstructionPrinter(Type.INS);  // create worker
//            String outputINS = insPrinter.print(programIns);  // worker works
//            System.out.println("==instructions are ==========================");
//            System.out.println(outputINS);
            
            // ================== ====================== ====================
            
            /* ***************** ****************** */
            // generate new program of instructions by processing
            InstructionClosureConverter insClosureConverter = new InstructionClosureConverter(programIns);
            ProgramInstruction programIns2 = insClosureConverter.convert();
            
            /* ***************** ****************** */
            // print instructions
//            String outputINS2 = insPrinter.print(programIns2);  // worker works
//            System.out.println("==instructions after closure conversion are ==========================");
//            System.out.println(outputINS2);
            
            // ================== ====================== ==================== 
            
            /* ***************** ****************** */
            // generate new program of instructions by processing
            ProgramInstruction programIns3 = InstructionProgramProcessor.processProgram(programIns2);
            
            /* ***************** ****************** */
            // print instructions
//            String outputINS3 = insPrinter.print(programIns3);  // worker works
//            System.out.println("==instructions after if transformation are ==========================");
//            System.out.println(outputINS3);
            
            // ================== ====================== ====================
            
            /* ***************** ****************** */
            // generating CSPS program
            CSPSTransformer cspsV = new CSPSTransformer();
            ProgramCSPS programCSPS = cspsV.trans(programIns3);
            
            /* ***************** ****************** */
            // print csps program
//            CSPSPrinter cspsPrinter = new CSPSPrinter();
//            String outputCSPS = cspsPrinter.printProgram(programCSPS);
//            System.out.println("==My CSPS code is ==========================");
//            System.out.println(outputCSPS);

//            File pathCSPS = FilenameUtils.changeExt(path, FilenameUtils.cCSPS);
//            FileWriter fwCSPS = new FileWriter(pathCSPS);
//            BufferedWriter bwCSPS = new BufferedWriter(fwCSPS);
//            bwCSPS.write(outputCSPS);
//            bwCSPS.close();
            
            //  ================== ====================== ====================
            
            /* ***************** ****************** */
            // generating patcsps program
            PatCspsTransformer patcspsV = new PatCspsTransformer();
            PModel programPCSPS = patcspsV.transProg(programCSPS);
            
            /* ***************** ****************** */
            // print patcsps program
            PATCSPSPrinter patcspsPrinter = new PATCSPSPrinter();
            String outputPATCSPS = patcspsPrinter.print(programPCSPS);
//            System.out.println("==PAT CSPS# code is ==========================");
//            System.out.println(outputPATCSPS);

            File pathPATCSPS = null;
            if (null == m_output) {
                pathPATCSPS = FilenameUtils.changeExt(path, FilenameUtils.cPATCSPS);
            } else {
                pathPATCSPS = new File(m_output);
            }
            FileWriter fwPATCSPS = new FileWriter(pathPATCSPS);
            BufferedWriter bwPATCSPS = new BufferedWriter(fwPATCSPS);
            bwPATCSPS.write(outputPATCSPS);
            bwPATCSPS.close();
                        
            /* ******** ******** */
            
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        } catch (RecognitionException e) {
            e.printStackTrace();
            return -1;
        } finally {
        }
        
        return 0;

    }

    public static void main(String argv[]) throws IOException {
        MGenerator tg = new MGenerator();
        if (tg.parseArgv(argv) != 0) {
            return;
        }

        if (tg.generateModel() != 0) {
            return;
        }

    }
}
