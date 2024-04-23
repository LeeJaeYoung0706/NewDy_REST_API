package toy_project.newdy.rest_api.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy_project.newdy.rest_api.common.lib.response.Response;

@RestController
@RequestMapping("/error_code")
public class ErrorCodeListController implements ErrorListControllerWithSwagger {

    @GetMapping("/list")
    @Override
    public Response<String> errorCodeList(){
        return Response.builder(200, "errorList").build();
    }
}
