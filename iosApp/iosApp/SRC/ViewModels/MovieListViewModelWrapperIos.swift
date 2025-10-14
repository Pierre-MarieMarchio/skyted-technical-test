import ComposeApp
import Combine

class MovieListViewModelWrapperIos: ObservableObject {
    let vm: MovieListVM

    @Published var movies: [MovieListItemDto] = []
    @Published var isLoading: Bool = false
    @Published var errorMessage: String? = nil

    init(vm: MovieListVM) {
        self.vm = vm

        vm.movieListState.addObserver { [weak self] moviesKotlin in
            guard let self = self else { return }

            DispatchQueue.main.async {
                if let kotlinArray = moviesKotlin as? [Any] {
                    self.movies = kotlinArray.compactMap { $0 as? MovieListItemDto }
                } else {
                    self.movies = []
                }
            }
        }

        vm.isLoading.addObserver { [weak self] loading in
            guard let self = self else { return }

            DispatchQueue.main.async {
                if let boolValue = loading as? KotlinBoolean {
                    self.isLoading = boolValue.boolValue
                } else if let boolValue = loading as? Bool {
                    self.isLoading = boolValue
                } else {
                    self.isLoading = false
                }
            }
        }

        vm.error.addObserver { [weak self] error in
            guard let self = self else { return }

            DispatchQueue.main.async {
                self.errorMessage = error as? String
            }
        }
    }

    func search(query: String) {
        vm.loadMovieList(query: query)
    }

    deinit {
        vm.movieListState.removeAllObservers()
        vm.isLoading.removeAllObservers()
        vm.error.removeAllObservers()
    }
}