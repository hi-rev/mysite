import React, { useEffect, useState } from 'react';
import MySiteLayout from "../../layout/MySiteLayout";
import styles from '../../assets/scss/component/main/Main.scss';

export default function Main() {
    // siteVo 데이터 가져오기
    const [site, setSite] = useState([]);

    const fetchSite = async () => {
        try {
             const response = await fetch('/api/site', {
                 method: 'get',
                 headers: {
                     'Accept': 'application/json'
                 }
             });
 
             if (!response.ok) {
                 throw new Error(`${response.status} ${response.statusText}`);
             }
 
             const json = await response.json();
             if (json.result !== 'success') {
                 throw new Error(`${json.result} ${json.message}`);
             }
 
             setSite(json.data);
         } catch (err) {
             console.error(err);
         }
    }

    useEffect(() => {
        fetchSite();
    }, []);

    return (
        <MySiteLayout>
            <div className={styles.siteIntroduction}>
                <li className={styles.siteImage}>
                    <span style={{
                        backgroundImage: `url(${site.profile})`
                    }}/>
                </li>
                    <h2>{site.welcome}</h2>
                    <p>
                        {site.description}
                        <br />
                        <a href="">방명록</a>에 글 남기기<br />
                    </p>
            </div>
        </MySiteLayout>
    );
}