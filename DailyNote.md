# 콜라톤 개발 일지

- ## 18.10.01

    ### 1. 네이버 Login API 적용
    
        - 단, Spring Security oauth와 적용하는 방법을 잘 모르겠어서 고민해볼 필요가 있음.
        
    ### 2. Controller 로직 구현
    
        - LoginController가 Login 담당, PetController는 Pet 관련 요청을 담당, History는 History 관련 요청 담당
        
        - History는 RestController로 Json파일을 전달하도록 구현 (Ajax를 위해)
        
        - 다만, Controller가 Url 전송이 아닌 로직 구현을 하고 있어 Strategy Pettern 적용 고려
    
    ### 3. JPA 적용하여 Entity, Repository 구현
    
        - Entity에 들어갈 Column들은 팀원과 최종 상의가 필요해보인다.
        
        - User의 경우는 네이버 로그인 후 정보 받아오는 용도로만 사용 (Entity 생성 X)
        
    ### 4. Service 객체 생성
    
        - Service 객체로 각 Entity에 적용할 Business Login 구현
        
- ## 18.10.03

    ### 1. Back-End 테스트 코드 작성
        
        - 설정한 URL들이 잘 불러와지는지, 애완동물 등록이 잘되는지 Test 코드 작성
        
    ### 2. View  작업
        
        - thymeleaf dialect 적용
        - layout을 위한 html 작성
        
 - ## 18.10.04
 
    ### 1. History 테스트 코드 작성
    
    ### 2. View 작업
    
        - index 페이지 layout 및 초기 작업
        
 - ## 18.10.06
 
    ### 1. View 작업
        - petList view 디자인
        - index 페이지 스타일링 완료
        
    ### 2. file 업로드 설정