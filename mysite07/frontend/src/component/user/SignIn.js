import React from 'react';
import MySiteLayout from "../../layout/MySiteLayout";
import styles from '../../assets/scss/component/user/User.scss';

export default function SignIn() {
    return (
        <MySiteLayout>
            <div className={styles.User}>
                <form className={styles.loginForm}>
                    <label className={styles.blockLabel}>이메일</label>
                    <input type='text' value='' />

                    <label className={styles.blockLabel}>패스워드</label>
                    <input type='password' value='' />

                    <input type='submit' value='로그인' />
                </form>
            </div>
        </MySiteLayout>
    );
}