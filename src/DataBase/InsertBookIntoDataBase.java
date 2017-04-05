package DataBase;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class InsertBookIntoDataBase {
    public String ReadFile(String Path){
        BufferedReader reader = null;
        String laststr = "";
        try{
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                laststr += tempString;
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }
    public static void main(String[] args) {
        DataBaseOperate operateTest = new DataBaseOperate();
//        InsertBookIntoDataBase file = new InsertBookIntoDataBase();
//        System.out.println(file.ReadFile("D:\\code\\python\\doubanSpider\\爱情\\1.json"));
        String path1 = "D:\\code\\python\\doubanSpider\\文学\\";
        int number = 2;
        String path2 = ".json";
        for(;number<1001;number++) {
            String JsonContext = "["+new InsertBookIntoDataBase().ReadFile(path1+number+path2)+"]";
            JSONArray jsonArray = JSONArray.fromObject(JsonContext);
            int size = jsonArray.size();
//        System.out.println("Size: " + size);
            for (int i = 0; i < size; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.get("title").toString();
                String author = jsonObject.get("author").toString();
                String ISBN = jsonObject.get("ISBN").toString();
                String authorIntroduction = jsonObject.get("authorIntroduction").toString();
                String briefIntroduction = jsonObject.get("briefIntroduction").toString();
                String pages = jsonObject.get("pages").toString();
                String press = jsonObject.get("press").toString();
                String price = jsonObject.get("price").toString();
                String publicationDate = jsonObject.get("publicationDate").toString();
                String translator = jsonObject.get("translator").toString();
                String cover = "BookInformation/文学/"+number+".jpg";
                int bookID = operateTest.addBookInformation(name, cover, author, translator, 60, 10004, publicationDate, press,
                        pages, authorIntroduction, briefIntroduction, ISBN, price);
                System.out.println("正在添加："+bookID);
//                System.out.println();
//            System.out.println(name);
//            System.out.println("[" + i + "]title=" + jsonObject.get("title"));
//            System.out.println("[" + i + "]author=" + jsonObject.get("author"));
//            System.out.println("[" + i + "]press=" + jsonObject.get("press"));
            }
        }
    }
}