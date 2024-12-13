package com.isn.quizplatform.model;

public class ApiResponse<T> {
    private T data; // Attribut générique pour contenir un objet de n'importe quel type
    private int code; // Attribut pour enregistrer le code de succès ou d'échec
    private String error; // Attribut pour enregistrer un message d'erreur

    // Constructeur par défaut
    public ApiResponse() {}

    // Constructeur paramétré (avec tous les attributs)
    public ApiResponse(T data, int code, String error) {
        this.data = data;
        this.code = code;
        this.error = error;
    }

    // Constructeur paramétré (sans data)
    public ApiResponse(int code, String error) {
        this.code = code;
        this.error = error;
    }

    // Getters et Setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Response{" +
                "data=" + data +
                ", code=" + code +
                ", error='" + error + '\'' +
                '}';
    }
}
