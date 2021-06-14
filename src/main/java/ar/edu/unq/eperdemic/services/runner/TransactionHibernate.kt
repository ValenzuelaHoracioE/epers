package ar.edu.unq.eperdemic.services.runner

import org.hibernate.Session

object TransactionHibernate: Transaction{
    private var transaction: org.hibernate.Transaction? = null
    private var session: Session? = null

    val currentSession: Session
        get() {
            if (session == null) {
                throw RuntimeException("No hay ninguna session en el contexto")
            }
            return session!!
        }

    override fun hasSession(): Boolean {
        return session != null
    }

    override fun start() {
        session = HibernateSessionFactoryProvider.instance.createSession()
        transaction = session!!.beginTransaction()
    }

    override fun commit() {
        transaction?.commit()
        session?.close()
        this.nullVar()
    }

    override fun rollback() {
        transaction?.rollback()
        session?.close()
        this.nullVar()
    }

    private fun nullVar(){
        session = null
        transaction = null
    }
}