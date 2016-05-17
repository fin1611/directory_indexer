import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DirectoryIndexer {

	static PrintWriter pw;
	
	public static void main(String[] args) 
		throws Exception
	{
		String path = args[0];
		
		System.out.println("Path: " + path);
		
		File resultFile = new File( new File(path), "index.txt");
		pw = new PrintWriter(new FileWriter(resultFile));
		
		scanDir( new File( path ) );
		
		pw.close();
		
		System.out.println("Finished");
	}
	
	private static List<String> scanDir( File dir )
			throws Exception
	{
		System.out.println(dir);
		
		List<String> fileNames = new ArrayList<String>();
		
		File[] files = dir.listFiles();
		for( File file : files )
		{
			if( file.getName().startsWith( ".") ) continue;
			
			if( file.isDirectory() )
			{
				List<String> subDirFileNames = scanDir( file );
				
				if( subDirFileNames.size() > 0 )
				{
					pw.println( file );
					
					for( String subFileName : subDirFileNames )
					{
						pw.println( subFileName );
					}
					
					pw.println();
				}
			}
			else
			{
				fileNames.add( file.getName() );
			}
		}
		
		return fileNames;
	}

}
