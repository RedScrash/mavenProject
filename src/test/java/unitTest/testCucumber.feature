# language: es
@tag
Característica: Primer test

  @tag1
  Escenario: Primera prueba
    Dado Usuario ingresa config
    Cuando Usuario ingresa en el campo "TXT_LOGIN_USER" el texto "user@user.com"
    Cuando Usuario ingresa en el campo "TXT_LOGIN_PASS" el texto "user123456"
    Cuando Usuario hace clic sobre el objeto "BTN_LOGIN_LOGIN"
    Y Usuario valida que el objeto "LBL_USER_INFO" este presente en pantalla
    Y Usuario finaliza escenario
