# language: es
@tag
Característica: Primer test

  @tag1
  Escenario: Primera prueba
    Dado Usuario ingresa config
    Cuando Usuario ingresa en el campo "TXT_LOGIN_USER" el texto "user@user.com"
    Y Usuario ingresa en el campo "TXT_LOGIN_PASS" el texto "user123456"
    Y Usuario hace clic sobre el objeto "BTN_LOGIN_LOGIN"
    Entonces Usuario valida que el objeto "LBL_USER_INFO" este presente en pantalla
    Entonces Usuario finaliza escenario

      @tag2
  Escenario: Login Correcto
    Dado Usuario ingresa config
    Cuando Usuario ingresa en el campo "TXT_LOGIN_USER" el texto "user@user.com"
    Y Usuario ingresa en el campo "TXT_LOGIN_PASS" el texto "user123456"
    Y Usuario hace clic sobre el objeto "BTN_LOGIN_LOGIN"
    Entonces Usuario valida que el objeto "LBL_USER_INFO" este presente en pantalla
    Entonces Usuario finaliza escenario