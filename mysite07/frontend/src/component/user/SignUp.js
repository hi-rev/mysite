import React from 'react';
import MySiteLayout from "../../layout/MySiteLayout";
import styles from '../../assets/scss/component/user/User.scss';

export default function SignUp() {
    return (
        <MySiteLayout>
            <div className={styles.User}>
                <form className={styles.joinForm} >
                    <label className={styles.blockLabel} for="name">이름</label>
                    <input name="email" type="text" value="" />

                    <label className={styles.blockLabel} for="email">이메일</label>
                    <input name="email" type="text" value="" />

                    <label className={styles.blockLabel}>패스워드</label>
                    <input name="password" type="password" value="" />

                    <fieldset>
                        <legend>성별</legend>
                        <label>여</label> <input type="radio" name='gender' value="female" checked="checked" />
                        <label>남</label> <input type="radio" name='gender' value="male" />
                    </fieldset>

                    <fieldset>
                        <legend>약관동의</legend>
                        <input className='agreeProv' type="checkbox" name='agreeProv' value='y' />
                        <label>서비스 약관에 동의합니다.</label>
                    </fieldset>

                    <input type='submit' value='가입하기' />
                </form>
            </div>
        </MySiteLayout>
    );
}