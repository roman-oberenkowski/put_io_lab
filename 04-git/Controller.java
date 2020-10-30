//forced rebase test1
//forgotten comment
package pl.poznan.put.cs.io.errors;
//start
//start-cd (imports)
import java.util.List;
//rebase test1

import pl.poznan.put.cs.io.errors.processors.BFSProcessor;
import pl.poznan.put.cs.io.errors.storage.DataInput;
import pl.poznan.put.cs.io.errors.storage.DataOutput;
//git status test

/**
 * This is the controller class, which reads the input, runs the computing and writes the output.
 */
public class Controller {

    private DataInput input;
    private DataOutput output;
    BFSProcessor bFSProcessor = new BFSProcessor();
	//stash test1
    /**
     *  @param inputName {String} input file path or console word for manual input
     *  @param outputName {String} output file path console word for standard output to the console
     */
    public Controller(String inputName, String outputName) {
        input = new DataInput(inputName);
        output = new DataOutput(outputName);
    }

    /**
     * Runs computation.
     */
    public void run() {
        //middle run coimmen
		//and finiths of comment midt
		try {
            input.load();
        } catch (Exception e) {
			//somethin gsomething
			//some other
			//and this to be
            System.err.println("Wrong input (see trace)!");
            e.printStackTrace();
            return;
        }
        // process
        List<Integer> result = bFSProcessor.process(input.getMatrix());

        // write results
        try {
            output.save(result);
        } catch (Exception e) {
            System.err.println("Wrong output (see trace)!");
            e.printStackTrace();
        }
    }
	//git stash test 3
	//forced rebase test 2
    /**
     * Main program interface for the executable
     * @param args  input "output" where input/output are names of files or "console" for standard in/out
     */
    public static void main(String[] args) {
        String inputName = null;
        String outputName = "console";
        if (args.length == 0) {
            System.err
                    .println("Run with parameters: input <output>"
                            + " where input/output are names of files or console for standard in/out");
            System.exit(-1);
        } else {
            inputName = args[0];
			//git rebase test2
            // second parameter is optional
            if (args.length > 1) {
                outputName = args[1];
            }
        }
        final Controller controler = new Controller(inputName,
		//git rebase test 3
                outputName);
				//end third comment
        controler.run();
    }
	//check if it is going to break 2
	//with simexd

}
