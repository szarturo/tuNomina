package demo

class ContactService {

	static transactional = true

	def findContacts(params) {
		def sortIndex = params.sidx ?: 'name'
		def sortOrder  = params.sord ?: 'asc'
		def maxRows = Integer.valueOf(params.rows)
		def currentPage = Integer.valueOf(params.page) ?: 1
		def rowOffset = currentPage == 1 ? 0 : (currentPage - 1) * maxRows

		def contacts = Contact.createCriteria().list(max: maxRows, offset: rowOffset) {
			if (params.firstName)
				ilike('firstName', "%${params.firstName}%")

			if (params.lastName)
				ilike('lastName', "%${params.lastName}%")

			if (params.email)
				ilike('email', "%${params.email}%")
				
			if (params.phone) {
				ilike('phone', "%${params.phone}%")
			}
			order(sortIndex, sortOrder)
		}

		def totalRows = contacts.totalCount
		def numberOfPages = Math.ceil(totalRows / maxRows)

		def results = contacts?.collect {
			[
				 cell: [
					it.firstName,
					it.lastName,
					it.email,
					it.phone
				 ],
				 id: it.id
			]
		}

		[rows: results, page: currentPage, records: totalRows, total: numberOfPages]
	}
}
