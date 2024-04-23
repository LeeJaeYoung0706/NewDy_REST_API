package toy_project.newdy.rest_api.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import toy_project.newdy.rest_api.common.lib.response.Response;

public interface ErrorListControllerWithSwagger {

    @Operation(
        summary = "Error code List",
        description = "에러 코드에 대한 정보"
    )
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "500" , description = "default = server error, 메세지 = 서버 오류: 알 수 없는 오류 입니다. 관리자에게 문의하세요."),
            @ApiResponse(responseCode = "501" , description = "default = server error, 메세지 = 서버 오류: 필수 파라미터가 존재하지 않습니다."),
            @ApiResponse(responseCode = "502" , description = "default = server error, 메세지 = 서버 오류: 잘못된 요청입니다."),
            @ApiResponse(responseCode = "503" , description = "default = server error, 메세지 = 서버 오류: 파일 크기가 올바르지 않습니다."),
            @ApiResponse(responseCode = "504" , description = "default = server error, 메세지 = 서버 오류: 파일 삭제 불가능 ( 외래키 )"),
            @ApiResponse(responseCode = "505" , description = "default = server error, 메세지 = 클라이언트 오류: 올바르지 않은 입력 값입니다."),
            @ApiResponse(responseCode = "300" , description = "default = authorization error, 메세지 = 인가 오류: 유효하지 않은 토큰입니다."),
            @ApiResponse(responseCode = "301" , description = "default = authorization error, 메세지 = 인가 오류: 만료된 토큰입니다."),
            @ApiResponse(responseCode = "400" , description = "default = authorization error, 메세지 = 인가 오류: 사용자를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "401" , description = "default = authorization error, 메세지 = 인가 오류: 로그인이 필요합니다."),
            @ApiResponse(responseCode = "403" , description = "default = authorization error, 메세지 = 인가 오류: 권한이 맞지 않습니다."),
            @ApiResponse(responseCode = "407" , description = "default = authorization error, 메세지 = 인가 오류: 회원이 존재하지 않습니다."),
            @ApiResponse(responseCode = "405" , description = "default = client error, 메세지 = 클라이언트 오류: 입력 값이 올바르지 않습니다."),
            @ApiResponse(responseCode = "406" , description = "default = client error, 메세지 = 클라이언트 오류: 필수 파라미터가 존재하지 않습니다."),
            @ApiResponse(responseCode = "412" , description = "default = client error, 메세지 = 클라이언트 오류: 전제 조건 실패."),
        }
    )
    Response<String> errorCodeList();
}
