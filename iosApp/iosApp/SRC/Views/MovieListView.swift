import SwiftUI
import ComposeApp

struct MovieListView: View {
    @StateObject private var viewModel: MovieListViewModelWrapperIos
    @State private var searchText = ""

    init(movieListVM: MovieListVM) {
        _viewModel = StateObject(wrappedValue: MovieListViewModelWrapperIos(vm: movieListVM))
    }

    var body: some View {
        NavigationView {
            VStack {
                TextField("Search movies...", text: $searchText)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .padding()
                    .onChange(of: searchText) { newValue in
                        viewModel.vm.updateSearchQuery(query: newValue)
                    }

                Button("Search") {
                    viewModel.search(query: searchText)
                }
                .padding()

                if viewModel.isLoading {
                    ProgressView("Loading...")
                } else if let error = viewModel.errorMessage {
                    Text("Error: \(error)")
                        .foregroundColor(.red)
                        .padding()
                } else if viewModel.movies.isEmpty {
                    Text("No movies found. Try searching!")
                        .foregroundColor(.gray)
                } else {
                    List(viewModel.movies, id: \.id) { movie in
                        VStack(alignment: .leading) {
                            Text(movie.title)
                                .font(.headline)
                        }
                        .padding(.vertical, 4)
                    }
                }
            }
            .navigationTitle("Movies")

        }
    }
}