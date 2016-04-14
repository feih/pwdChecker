package pwd.storm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by feih on 4/13/16.
 */

@Controller
public class WebController {

    @Autowired
    PasswordChecker passwordCheckerService;

    @RequestMapping("/checkpwd")
    public @ResponseBody Status checkPassword(@RequestParam(value="input", required=true) String rawPassword) {

        Status status = new Status();

        status.setServiceName(passwordCheckerService.getClass().getSimpleName());

        String checkerResult = passwordCheckerService.doTask(rawPassword);
        status.setServiceResult(checkerResult);
        status.setSuccess(PasswordChecker.PASSED.equals(checkerResult));

        return status;
    }
}
