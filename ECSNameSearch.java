import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
public class ECSNameSearch
{
  public static void main(String[] args)
  {
    try
    {
      //creating a new BufferedReader object for user input
      BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));

      // get user to input email id
      System.out.println("Enter the email id:");
      String id = reader.readLine();

      // construct url from user input
      String webAddress = new String("https://www.ecs.soton.ac.uk/people/");
      String fullWebAddress = webAddress.concat(id);
      URL url = new URL(fullWebAddress);

      // create BufferedReader object for html and get the right html line
      BufferedReader htmlReader = new BufferedReader(new InputStreamReader(url.openStream()));
      String html = getHTMLline(htmlReader);

      // print name sub string
      System.out.println(html.substring(html.indexOf(">", html.indexOf(" property=\"name\"")) + 1, html.indexOf("<", html.indexOf(" property=\"name\""))));
    }
    catch (IOException ioe)
    {
      System.out.println("IO Exception raised!");
    }
  }

  private static String getHTMLline(BufferedReader reader)
  {
   String html = "";
   try
   {
     // loop over html lines until line containing the right tag is found then break
     while((html = reader.readLine()) != null)
      {
        if(html.contains("<h1 class"))
        {
          break;
        }
      }
   }
   catch (IOException ioe)
   {
     System.out.println("IO Exception raised!");
   }
   return html;
  }
}
