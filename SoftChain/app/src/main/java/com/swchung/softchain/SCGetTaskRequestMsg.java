package com.swchung.softchain;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
/**
 * Created by icete on 2016-04-09.
 */
public class SCGetTaskRequestMsg extends SCMsg implements ISCRequestMsg {
    public static final String API_URL = "https://api.github.com";

    @Override
    public String toJson() {
        return null;
    }

    @Override
    public void sendMsg() {

    }

    public interface IGetTask {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<SCTypeTask>> tasks(
                @Path("owner") String owner,
                @Path("repo") String repo);
    }

    // Create a very simple REST adapter which points the GitHub API.
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // Create an instance of our GitHub API interface.
    IGetTask iGetTask = retrofit.create(IGetTask.class);

    // Create a call instance for looking up Retrofit contributors.
    Call<List<SCTypeTask>> call = iGetTask.tasks("square", "retrofit");

    // Fetch and print a list of the contributors to the library.
    /*
    List<SCTypeTask> tasks = call.execute().body();
    for (SCTypeTask task : tasks) {
        //System.out.println(task.login + " (" + task.contributions + ")");
    }
    */
}
