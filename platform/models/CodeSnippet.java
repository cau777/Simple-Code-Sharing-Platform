package platform.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "Snippets")
public class CodeSnippet {
    @Id
    @Column
    private String id;

    @Column(name = "text")
    private String codeText;

    @Column(name = "date")
    private LocalDateTime uploadDate;

    @Column(name = "timeLimit")
    private Long timeLimit;

    @Column(name = "viewsLeft")
    private Long viewsLeft;

    public CodeSnippet() {
    }

    public CodeSnippet(String codeText, long viewSeconds, long viewNumber) {
        this.codeText = codeText;

        UUID uuid = UUID.randomUUID();
        id = uuid.toString();

        uploadDate = LocalDateTime.now();

        this.timeLimit = viewSeconds <= 0 ? null : viewSeconds;
        this.viewsLeft = viewNumber <= 0 ? null : viewNumber;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    @JsonProperty("code")
    public String getCodeText() {
        return codeText;
    }

    @JsonProperty("date")
    public String getFormattedUploadDate() {
        return uploadDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @JsonProperty("time")
    public long getSecondsLeft() {
        if (timeLimit == null) return 0;
        return Math.max(0, timeLimit - Duration.between(uploadDate, LocalDateTime.now()).getSeconds());
    }

    @JsonProperty("views")
    public long getViewsLeft() {
        if (viewsLeft == null) return 0;
        return viewsLeft;
    }

    @JsonIgnore
    public boolean isRestrictedTime() {
        return timeLimit != null;
    }

    @JsonIgnore
    public boolean isRestrictedViews() {
        return viewsLeft != null;
    }

    public void decreaseView() {
        viewsLeft--;
    }

    @Override
    public String toString() {
        return "CodeSnippet{" +
                "id='" + id + '\'' +
                ", codeText='" + codeText + '\'' +
                ", uploadDate=" + uploadDate +
                ", secondsLeft=" + timeLimit +
                ", viewsLeft=" + viewsLeft +
                '}';
    }
}
