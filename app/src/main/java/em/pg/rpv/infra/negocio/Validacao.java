package em.pg.rpv.infra.negocio;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacao {
    private static Validacao validacaoUtil = new Validacao();

    private Validacao(){
    }

    public static Validacao getValidacaoUtil(){
        return validacaoUtil;
    }

    public boolean isEmailValid(CharSequence email) { return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches(); }

    public boolean isFieldEmpty(EditText campo){ return TextUtils.isEmpty(campo.getText().toString()); }

    public boolean hasSpacePassword(EditText campo){
        String senha = campo.getText().toString();
        Pattern p= Pattern.compile("^[^\\s]+$");
        Matcher m = p.matcher(senha);
        return m.matches();
    }

}
