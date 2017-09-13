package hello;

import com.google.common.io.ByteStreams;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class GreetingController {

    private static final String filepath = "/tmp/file.dat";

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }


    @RequestMapping(path ="/done", method = RequestMethod.GET)
    public void generateReport(HttpServletResponse response) throws Exception {
        FileInputStream in = null;

        try{
            in = new FileInputStream("/tmp/1.pdf");

        }catch (Exception e){

        }
        //byte[] data = IOUtils.toByteArray(in);
        byte[] data = ByteStreams.toByteArray(in);
        streamReport(response, data, "my_report.pdf");
    }


    protected void streamReport(HttpServletResponse response, byte[] data, String name)
            throws IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=" + name);
        response.setContentLength(data.length);

        response.getOutputStream().write(data);
        response.getOutputStream().flush();
    }

}
