package com.junga.wakatracker.retrofit;

public class Data {

    private Float duration;


    private String user_id;

    private String created_at;

    private String project;


    private String id;

    private String time;

    private String machine_name_id;

    public Float getDuration ()
    {
        return duration;
    }

    public void setDuration (Float duration)
    {
        this.duration = duration;
    }



    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getProject ()
    {
        return project;
    }

    public void setProject (String project)
    {
        this.project = project;
    }



    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    public String getMachine_name_id ()
    {
        return machine_name_id;
    }

    public void setMachine_name_id (String machine_name_id)
    {
        this.machine_name_id = machine_name_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [duration = "+duration+", user_id = "+user_id+", created_at = "+created_at+", project = "+project+", id = "+id+", time = "+time+", machine_name_id = "+machine_name_id+"]";
    }
}
