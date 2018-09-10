# language: es
@tag
Característica: Login PrimeSuite

  @tag1
  Escenario: Login Correcto
    Dado Usuario ingresa config
    Cuando Usuario ingresa en el campo "TXT_LOGIN_USER" el texto "Primeuser"
    Y Usuario ingresa en el campo "TXT_LOGIN_PASS" el texto "Primepass"
    Y Usuario hace clic sobre el objeto "BTN_LOGIN_LOGIN"
    Entonces Usuario valida que el objeto "LBL_WELCOME" este presente en pantalla
    Y Usuario finaliza escenario

 Escenario: Login sin usuario
    Dado Usuario ingresa config
    Cuando Usuario ingresa en el campo "TXT_LOGIN_USER" el texto ""
    Y Usuario ingresa en el campo "TXT_LOGIN_PASS" el texto "Primepass"
    Y Usuario hace clic sobre el objeto "BTN_LOGIN_LOGIN"
    Entonces Usuario valida el mensaje "El usuario está vacío" en el objeto "LBL_LOGIN_ERROR"
    Y Usuario finaliza escenario

 Escenario: Login sin pass
    Dado Usuario ingresa config
    Cuando Usuario ingresa en el campo "TXT_LOGIN_USER" el texto "Primeuser"
    Y Usuario ingresa en el campo "TXT_LOGIN_PASS" el texto ""
    Y Usuario hace clic sobre el objeto "BTN_LOGIN_LOGIN"
    Entonces Usuario valida el mensaje "La contraseña esta vacía" en el objeto "LBL_LOGIN_ERROR"
    Y Usuario finaliza escenario

 Escenario: Login sin usuario ni pass
    Dado Usuario ingresa config
    Cuando Usuario ingresa en el campo "TXT_LOGIN_USER" el texto ""
    Y Usuario ingresa en el campo "TXT_LOGIN_PASS" el texto ""
    Y Usuario hace clic sobre el objeto "BTN_LOGIN_LOGIN"
    Entonces Usuario valida el mensaje "El usuario y la contraseña están vacíos" en el objeto "LBL_LOGIN_ERROR"
    Y Usuario finaliza escenario