//
//  NewsDetailViewController.m
//  sourcing
//
//  Created by wuzhu on 13-1-8.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

#import "NewsDetailViewController.h"
#import "MobileInfoService.h"
#import "ToastHintUtil.h"
#import "StringUtil.h"

@interface NewsDetailViewController ()

@end

@implementation NewsDetailViewController

-(id)initWithBaseVO:(BaseVO *)kvo title:(NSString *)ktitle favchangeDelegate:(NSObject<FavDataChangeDelegate> *)delegate
{
    self = [super init];
    if(self)
    {
        _baseVO = kvo;
        _titleText = ktitle;
        _favDelegate = nil;
    }
    return self;
}

-(id)initWithBaseVO:(BaseVO *)kvo title:(NSString *)ktitle
{
    return [self initWithBaseVO:kvo title:ktitle favchangeDelegate:nil];
}

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    self.labelTitle.text = _titleText;
    //判断是否已经收藏过
    NSInteger index = [[FavoriteUtil favoriteUtil] hasFavData:_baseVO];
    [self setFavImageView:index!=-1];
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)viewDidUnload {
    [self setButtonReturn:nil];
    [self setLabelTitle:nil];
    [self setButtonFav:nil];
    [self setButtonShare:nil];
    [self setWebviewDetail:nil];
    [super viewDidUnload];
}
- (IBAction)actionReturn:(id)sender {
    [self dismissModalViewControllerAnimated:YES];
}

- (IBAction)actionFav:(id)sender {
    NSInteger index = [[FavoriteUtil favoriteUtil] hasFavData:_baseVO];
    if(index==-1)
    {
        [[FavoriteUtil favoriteUtil] addFavData:_baseVO];
        [self setFavImageView:YES];
        [ToastHintUtil showHint:NSLocalizedString(@"已添加到“我的收藏”", @"收藏提示") parentview:self.view];
        if(_favDelegate!=nil)
            [_favDelegate hasAddFavData:0 data:_baseVO];
        return;
    }
    [[FavoriteUtil favoriteUtil] removeFavData:_baseVO];
    [self setFavImageView:NO];
    [ToastHintUtil showHint:NSLocalizedString(@"已取消收藏", @"取消收藏提示") parentview:self.view];
    if(_favDelegate!=nil)
        [_favDelegate hasRemoveFavData:index data:_baseVO];
}

- (IBAction)actionShare:(id)sender {
}

-(void)setFavImageView:(BOOL)kfav
{
    if (kfav) {
        [self.buttonFav setBackgroundImage:[UIImage imageNamed:@"icon_faved"] forState:UIControlStateNormal];
    }else
    {
        [self.buttonFav setBackgroundImage:[UIImage imageNamed:@"icon_fav"] forState:UIControlStateNormal];
    }
}

-(void)serviceResult:(ResponseVO *)result
{
    //TODO
}
@end
