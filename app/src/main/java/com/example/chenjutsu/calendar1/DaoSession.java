package com.example.chenjutsu.calendar1;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoConfig;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.IdentityScopeType;

import com.example.chenjutsu.calendar1.EVENT;

import com.example.chenjutsu.calendar1.EVENTDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig eVENTDaoConfig;

    private final EVENTDao eVENTDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        eVENTDaoConfig = daoConfigMap.get(EVENTDao.class).clone();
        eVENTDaoConfig.initIdentityScope(type);

        eVENTDao = new EVENTDao(eVENTDaoConfig, this);

        registerDao(EVENT.class, eVENTDao);
    }
    
    public void clear() {
        eVENTDaoConfig.getIdentityScope().clear();
    }

    public EVENTDao getEVENTDao() {
        return eVENTDao;
    }

}
