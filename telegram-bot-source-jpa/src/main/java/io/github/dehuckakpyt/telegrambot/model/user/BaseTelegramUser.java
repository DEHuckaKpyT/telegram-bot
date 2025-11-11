package io.github.dehuckakpyt.telegrambot.model.user;


import io.github.dehuckakpyt.telegrambot.model.UUIDTable;
import io.github.dehuckakpyt.telegrambot.model.source.TelegramUser;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

/**
 * @author Denis Matytsin
 */
@MappedSuperclass
public class BaseTelegramUser extends UUIDTable implements TelegramUser {

    @Column(nullable = false, unique = true)
    private long userId;

    private String username;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    private String languageCode;

    @Column(nullable = false)
    private boolean available;

    @Column(nullable = false)
    private LocalDateTime updateDate;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public @Nullable String getUsername() {
        return username;
    }

    @Override
    public @NotNull String getFirstName() {
        return firstName;
    }

    @Override
    public @Nullable String getLastName() {
        return lastName;
    }

    @Override
    public @Nullable String getLanguageCode() {
        return languageCode;
    }

    @Override
    public boolean getAvailable() {
        return available;
    }

    @Override
    public @NotNull LocalDateTime getUpdateDate() {
        return updateDate;
    }

    @Override
    public @NotNull LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUsername(@Nullable String username) {
        this.username = username;
    }

    public void setFirstName(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(@Nullable String lastName) {
        this.lastName = lastName;
    }

    public void setLanguageCode(@Nullable String languageCode) {
        this.languageCode = languageCode;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setUpdateDate(@NotNull LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public void setCreateDate(@NotNull LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
