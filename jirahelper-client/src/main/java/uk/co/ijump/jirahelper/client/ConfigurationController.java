package uk.co.ijump.jirahelper.client;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.co.ijump.jirahelper.client.configuration.JiraConfigurationSection;

/**
 * Created by brianmason on 08/08/2016.
 */

@Controller
public class ConfigurationController {

    @RequestMapping("/config")
    public String getConfiguration(@RequestParam(value="description", defaultValue="Kata Description") String description, @RequestParam(value="id", defaultValue="1") int id) {

        return new JiraConfigurationSection().getRootUrl();
    }

}

