package u.a.r.g.searchonrole;

/**
 * Created by Hugo on 28/05/2016.
 */
public class Role {
    private int id;
    private String nome;
    private String data;
    private float custo;
    private String descricao;
    private String local;
    private String /*[]*/ bebidas;

    public Role () {

    }

    public Role (String nome, String data, float custo, String Descricao, String local, String /*[]*/ bebidas) {
        this.nome = nome;
        this.data = data;
        this.custo = custo;
        this.descricao = descricao;
        this.local = local;
        this.bebidas = bebidas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String/*[]*/ getBebidas() {
        return bebidas;
    }

    public void setBebidas(String/*[]*/ bebidas) {
        this.bebidas = bebidas;
    }
}
